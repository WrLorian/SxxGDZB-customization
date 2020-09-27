package com.kiwihouse.service.impl;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.MapKeyComparator;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dao.entity.DataTimeNum;
import com.kiwihouse.dao.entity.DateStatisList;
import com.kiwihouse.dao.mapper.AlarmMapper;
import com.kiwihouse.dao.mapper.DataStatisticsMapper;
import com.kiwihouse.dao.mapper.EquipmentMapper;
import com.kiwihouse.service.DataStatisticsService;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.DataStatisticsVo;
import com.kiwihouse.vo.kiwihouse.EqptQueryVo;

@Service
public class DataStatisticsServiceImpl implements DataStatisticsService{

	@Autowired
    DataStatisticsMapper dataStatisticsMapper;
	@Autowired
	EquipmentMapper equipmentMapper;
	@Autowired
	AlarmMapper alarmMapper;
    /**
     * 查询数据总览信息
     * @param dataStatisticsVo 查询参数
     * @return
     */
//	@Override
//    public ResultList queryInfo(DataStatisticsVo dataStatisticsVo) {
//    	System.out.println(dataStatisticsVo.getStartTime() + "-" + dataStatisticsVo.getEndTime());
//    	DateStatis dateStatis = dataStatisticsMapper.queryDataStatois(dataStatisticsVo);
//    	DateStatisList dateStatisList = new DateStatisList();
//    	if(dateStatis != null) {
//    		dateStatisList.setOverCurrentAlarm(Arrays.asList(dateStatis.getOverCurrentAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setOverloadAlarms(Arrays.asList(dateStatis.getOverloadAlarms().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setUnderVoltageAlarm(Arrays.asList(dateStatis.getUnderVoltageAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setLeakageAlarm(Arrays.asList(dateStatis.getLeakageAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setTemperatureAlarm(Arrays.asList(dateStatis.getTemperatureAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setUseingTheAlarm(Arrays.asList(dateStatis.getUseingTheAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setSmokeAlarm(Arrays.asList(dateStatis.getSmokeAlarm().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setElectricAlarmTotalFailure(Arrays.asList(dateStatis.getElectricAlarmTotalFailure().split(",")).stream().mapToInt(Integer::parseInt).toArray());
//        	dateStatisList.setAddTime(dateStatis.getAddTime().split(","));
//    	}
//    	
//    	//用电设备总数
//    	EqptQueryVo eqptQueryVo = new EqptQueryVo();
//    	eqptQueryVo.setEqptType("0");
//    	int dxCount = equipmentMapper.queryInfoCount(eqptQueryVo);
//    	eqptQueryVo.setEqptType("1");
//    	int sxCount = equipmentMapper.queryInfoCount(eqptQueryVo);
//    	int electricAlarms = alarmMapper.timeAlarmCount(dataStatisticsVo.getStartTime(),dataStatisticsVo.getEndTime(),"0,1");
//    	dateStatisList.setElectricAlarmNum(electricAlarms);
//    	dateStatisList.setElectricAlarmNums(dxCount + sxCount);
//    	//烟感设备总数.
//    	eqptQueryVo.setEqptType("2");
//    	int ygCount = equipmentMapper.queryInfoCount(eqptQueryVo);
//    	//烟感告警设备数量
//    	int ygAlarmCount = alarmMapper.timeAlarmCount(dataStatisticsVo.getStartTime(),dataStatisticsVo.getStartTime(),"2");
//    	dateStatisList.setSmokeNum(ygCount);
//    	dateStatisList.setSmokeAlarmNum(ygAlarmCount);
//    	
//    	
//        return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(1,dateStatisList));
//
//    }
	@Override
	public ResultList queryInfo(DataStatisticsVo dataStatisticsVo) {
		List<Date> list = TimeUtil.getBetweenDates(TimeUtil.strToDate(dataStatisticsVo.getStartTime()),TimeUtil.strToDate(dataStatisticsVo.getEndTime()));
		DateStatisList dateStatisList = new DateStatisList();
//		System.out.println(list.size());
//		dateStatisList.setAddTime(list.toArray(addTime));
		for(int i = 1;i<9;i++) {
			 List<DataTimeNum> listData = alarmMapper.selectByTimesAndType(dataStatisticsVo,i);
			 Map<String,Integer> mapl = new HashMap<String, Integer>();
			 List<Map<String,Integer>> strList = new ArrayList<Map<String,Integer>>();
			 list.forEach(l ->{
				 mapl.put(TimeUtil.dateToStr(l), 0);
				 Map<String,Integer> map2 = new HashMap<String, Integer>();
				 map2.put(TimeUtil.dateToStr(l), 0);
				 strList.add(map2);
				 
			 });
			 listData.forEach(dataTimeNum ->{
				 	 mapl.put(dataTimeNum.getAddTime(), dataTimeNum.getNum());
				 });
			 if(i == 1) {
				dateStatisList.setOverCurrentAlarm(dataTo(mapl));
			 }else if(i == 2) {
				 dateStatisList.setOverloadAlarms(dataTo(mapl));
			 }else if(i == 3) {
				 dateStatisList.setUnderVoltageAlarm(dataTo(mapl));
			 }else if(i == 4) {
				 dateStatisList.setLeakageAlarm(dataTo(mapl));
			 }else if(i == 5) {
				 dateStatisList.setTemperatureAlarm(dataTo(mapl));
			 }else if(i == 6) {
				 dateStatisList.setUseingTheAlarm(dataTo(mapl));
			 }else if(i == 7) {
				 dateStatisList.setSmokeAlarm(dataTo(mapl));
			 }else if(i == 8) {
				 dateStatisList.setElectricAlarmTotalFailure(dataTo(mapl));
			 }
		}
		
		
		EqptQueryVo eqptQueryVo = new EqptQueryVo();
    	eqptQueryVo.setEqptType("0");
    	int dxCount = equipmentMapper.queryInfoCount(eqptQueryVo);
    	eqptQueryVo.setEqptType("1");
    	int sxCount = equipmentMapper.queryInfoCount(eqptQueryVo);
    	int electricAlarms = alarmMapper.timeAlarmCount(dataStatisticsVo.getStartTime(),dataStatisticsVo.getEndTime(),"0,1");
    	dateStatisList.setElectricAlarmNum(electricAlarms);
    	dateStatisList.setElectricAlarmNums(dxCount + sxCount);
    	//烟感设备总数.
    	eqptQueryVo.setEqptType("2");
    	int ygCount = equipmentMapper.queryInfoCount(eqptQueryVo);
    	//烟感告警设备数量
    	int ygAlarmCount = alarmMapper.timeAlarmCount(dataStatisticsVo.getStartTime(),dataStatisticsVo.getStartTime(),"2");
    	dateStatisList.setSmokeNum(ygCount);
    	dateStatisList.setSmokeAlarmNum(ygAlarmCount);
		return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(1,dateStatisList));
	}
	public int [] dataTo(Map<String,Integer> map) {
		Map<String, Integer> resultMap = sortMapByKey(map); 
		String strArr = "";
		for(Map.Entry<String, Integer> entry : resultMap.entrySet()){
		    String mapKey = entry.getKey();
		    String mapValue = entry.getValue().toString();
		    strArr += mapValue + ",";
		}
		System.out.println(strArr);
		return Arrays.asList(strArr.split(",")).stream().mapToInt(Integer::parseInt).toArray();
	}
	
	
	/**

     * 使用 Map按key进行排序

     * @param map

     * @return

     */

    public static Map<String, Integer> sortMapByKey(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Integer> sortMap = new TreeMap<String, Integer>( new MapKeyComparator());
        sortMap.putAll(map);
        return sortMap;
    }
}
