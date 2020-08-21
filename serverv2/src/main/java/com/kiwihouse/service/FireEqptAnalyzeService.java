package com.kiwihouse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.common.bean.AlmSta;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.MtSta;
import com.kiwihouse.common.utils.FileThread;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dto.AlarmMsgDouble;
import com.kiwihouse.dto.AlmAndEqptNumDto;
import com.kiwihouse.dto.AlmAndEqptNumReturnDto;
import com.kiwihouse.dto.Analyze;
import com.kiwihouse.dto.FireEqptAnalyzeDto;
import com.kiwihouse.mapper.FireEqptAnalyzeMapper;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.DataAnalyzeVo;

/**
 * @author yjzn
 * @date 2020-02-27-下午 6:24
 */
@Service
public class FireEqptAnalyzeService {

    @Autowired
    FireEqptAnalyzeMapper fireEqptAnalyzeMapper;

    public ResultList analyzeSubCodeInfo(String code, String adminId, boolean topMg) {
        if(StringUtils.isNotBlank(code)) {
            int length = code.length();
            ConcurrentHashMap<String, String> codeMap = new ConcurrentHashMap<>();
            try {
                if (2 == length) {
                    codeMap = new FileThread().getCodeMap("file/china-City.txt", code);
                } else if (4 == length) {
                    codeMap = new FileThread().getCodeMap("file/china-County.txt", code);
                } else if (6 == length) {
                    codeMap = new FileThread().getCodeMap("file/china-County.txt",code);
                }else{
                    return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "code格式不正确，必须为2、4、6位", null);
                }
            } catch (Exception e) {
                return new ResultList(Code.QUERY_FAIL.getCode(), Code.QUERY_FAIL.getMsg(), null);
            }
            if(0==codeMap.size()){
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "code不存在", null);
            }
            return querySubCode(adminId, topMg, codeMap);
        }else{
            return queryAllCode(adminId,topMg);
        }
    }

    public ResultList querySubCode(String adminId, boolean topMg,ConcurrentHashMap<String, String> codeMap){
        HashMap<String, AlmAndEqptNumReturnDto> map = new HashMap<>();
        try {
            codeMap.forEach((key,value)->{
                AlmAndEqptNumReturnDto almAndEqptNumReturnDto = queryOneCode(key, adminId, topMg);
                map.put(key,almAndEqptNumReturnDto);
            });
        }catch(Exception e) {
            return new ResultList(Code.QUERY_FAIL.getCode(), Code.QUERY_FAIL.getMsg(),null);
        }

        return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(),new Result<>(map.size(),map));
    }

    /**
     * 根据code查询火警、烟感设备数、工单数据、告警数
     * 根据code查询火警、烟感设备数、工单数据、告警数
     * @param code
     * @param adminId
     * @param topMg
     * @return
     */
    public ResultList analyzeCodeInfo(String code, String adminId, boolean topMg) {
        if(StringUtils.isNotBlank(code)) {
            AlmAndEqptNumReturnDto almAndEqptNumReturnDto = queryOneCode(code, adminId, topMg);
            if(null==almAndEqptNumReturnDto){
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"code不存在",null);
            }else{
                return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(3,almAndEqptNumReturnDto));
            }
        }else{
            return queryAllCode(adminId,topMg);
        }
    }

    /**
     * 查询所有的省的数据
     * @param adminId
     * @param topMg
     * @return
     */
    public ResultList queryAllCode(String adminId, boolean topMg){
        HashMap<String, AlmAndEqptNumReturnDto> map = new HashMap<>();
        try {
            ConcurrentHashMap<String, String> codeMap = new FileThread().getCodeMap("file/china-Province.txt");
            codeMap.forEach((key,value)->{
                AlmAndEqptNumReturnDto almAndEqptNumReturnDto = queryOneCode(key, adminId, topMg);
                map.put(key,almAndEqptNumReturnDto);
            });
        }catch(Exception e) {
            return new ResultList(Code.QUERY_FAIL.getCode(), Code.QUERY_FAIL.getMsg(),null);
        }

        return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(),new Result<>(map.size(),map));
    }

    /**
     * 查询某个code对应的数据
     * @param code
     * @param adminId
     * @param topMg
     * @return
     */
    private AlmAndEqptNumReturnDto queryOneCode(String code, String adminId, boolean topMg){
        AlmAndEqptNumReturnDto almAndEqptNumReturnDto = new AlmAndEqptNumReturnDto();

        //省市区adminId
        HashMap<String,Object> map = transferCode(code,adminId,topMg);
        if(null== map){
            return null;
        }
        //查询告警未处理的设备数和告警数
        map.put("alarmStatus",AlmSta.UNPROCESSED.getCode());
        AlmAndEqptNumDto almAndEqptNumDto0 = queryAlmNums(map);
        //查询告警已转为工单的设备数和告警数
        map.put("alarmStatus",AlmSta.TO_ORDER.getCode());
        AlmAndEqptNumDto almAndEqptNumDto1 = queryAlmNums(map);
        //查询告警已处理的设备数和告警数
        map.put("alarmStatus",AlmSta.PROCESSED.getCode());
        AlmAndEqptNumDto almAndEqptNumDto2 = queryAlmNums(map);

        almAndEqptNumReturnDto.setAlmUnprocessed(almAndEqptNumDto0);
        almAndEqptNumReturnDto.setAlmToOrder(almAndEqptNumDto1);
        almAndEqptNumReturnDto.setAlmProcessed(almAndEqptNumDto2);

        //查询总设备数量
        Integer totalNum = queryEqptNums(map);
        //查询烟感设备数量
        map.put("eqptType","0");
        Integer fireNum = queryEqptNums(map);
        //查询火警设备数量
        map.put("eqptType","1");
        Integer smokeNum = queryEqptNums(map);

        almAndEqptNumReturnDto.setTotalNum(totalNum);
        almAndEqptNumReturnDto.setFireNum(fireNum);
        almAndEqptNumReturnDto.setSmokeNum(smokeNum);

        map.put("mtStatus", MtSta.UNPROCESSED.getCode());
        Integer mtUnprocessed = queryOrderNum(map);
        map.put("mtStatus", MtSta.PROCESSED.getCode());
        Integer mtProcessed = queryOrderNum(map);
        map.put("mtStatus", MtSta.CANCEL.getCode());
        Integer mtCancel = queryOrderNum(map);

        almAndEqptNumReturnDto.setMtUnprocessed(mtUnprocessed);
        almAndEqptNumReturnDto.setMtProcessed(mtProcessed);
        almAndEqptNumReturnDto.setMtCancel(mtCancel);

        return almAndEqptNumReturnDto;
    }

    //将code对应的省市区保存到AnalyzeCodeVo中
    private HashMap<String,Object> transferCode(String code,String adminId,boolean topMg){
        HashMap<String, Object> map = new HashMap<>();
        if (topMg) {
            map.put("adminId", null);
        } else {
            map.put("adminId", adminId);
        }
        try {
            ConcurrentHashMap<String, String> codeMap = new FileThread().getCodeMap("file/china-Province.txt");
            ConcurrentHashMap<String, String> codeMapCity = new FileThread().getCodeMap("file/china-City.txt");
            ConcurrentHashMap<String, String> codeMapCounty = new FileThread().getCodeMap("file/china-County.txt");

            String province = codeMap.get(code);
            map.put("province", province);
            String city = codeMapCity.get(code);
            map.put("city", city);
            String district = codeMapCounty.get(code);
            map.put("district", district);

            if (null == province && null == city && null == district) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return map;

    }

    //查询告警未处理的、告警已转为工单的、告警已处理的设备数和告警数
    private AlmAndEqptNumDto queryAlmNums(HashMap<String,Object> map){
        return fireEqptAnalyzeMapper.queryAlmNums(map);
    }

    //根据code查询火警设备数和烟感设备数
    private Integer queryEqptNums(HashMap<String,Object> map){
        return fireEqptAnalyzeMapper.queryEqptNums(map);
    }

    private Integer queryOrderNum(HashMap<String,Object> map){
        return fireEqptAnalyzeMapper.queryOrderNum(map);
    }





    /**
     *
     * @param dataAnalyzeVo
     * @param adminId
     * @param topMg
     * @return
     */
    public ResultList queryInfo(DataAnalyzeVo dataAnalyzeVo, String adminId, boolean topMg) {

        List<Double> curList = new ArrayList<>();
        List<Double> tempList = new ArrayList<>();
        List<Double> leakList = new ArrayList<>();
        List<Double> powerList = new ArrayList<>();
        List<Double> kwhList = new ArrayList<>();


        //一级管理员可查询全部
        if(topMg){
            dataAnalyzeVo.setAdminId(null);
        }else{
            dataAnalyzeVo.setAdminId(adminId);
        }

        String startTime = dataAnalyzeVo.getStartTime();
        String endTime = dataAnalyzeVo.getEndTime();

        List<FireEqptAnalyzeDto> list = fireEqptAnalyzeMapper.queryInfo(dataAnalyzeVo);         //其他的数据求最大值
        List<FireEqptAnalyzeDto> totalList = fireEqptAnalyzeMapper.queryAll(dataAnalyzeVo);     //kwh求和
        List<String> dayList = getDayList(startTime, endTime);

        String[] bigCur = {""};
        String[] bigPower = {""};
        String[] bigLeak = {""};
        String[] bigTemp = {""};

        AtomicReference<Double> curBiggest = new AtomicReference<>(0.0);
        AtomicReference<Double> tempBiggest = new AtomicReference<>(0.0);
        AtomicReference<Double> leakBiggest = new AtomicReference<>(0.0);
        AtomicReference<Double> powerBiggest = new AtomicReference<>(0.0);

        dayList.forEach(time->{
            List<FireEqptAnalyzeDto> collect = list.stream().filter(fireEqptAnalyzeDto -> fireEqptAnalyzeDto.getAddTime().startsWith(time)).collect(Collectors.toList());
            collect.forEach(fireEqptAnalyzeDto -> {
                String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);
                String addTime = fireEqptAnalyzeDto.getAddTime();

                Double cur1 = alarmMsgDouble.getCur();
                if(cur1>curBiggest.get()){
                    bigCur[0] = addTime;
                    curBiggest.set(cur1);
                }
                Double pwr = alarmMsgDouble.getPwr();
                if(pwr>powerBiggest.get()){
                    bigPower[0] = addTime;
                    powerBiggest.set(pwr);
                }
                Double leak_cur = alarmMsgDouble.getLeak_cur();
                if(leak_cur>leakBiggest.get()){
                    bigLeak[0] = addTime;
                    leakBiggest.set(leak_cur);
                }
                Double lineTemp = alarmMsgDouble.getLine_temp();
                if(lineTemp>tempBiggest.get()){
                    bigTemp[0] = addTime;
                    tempBiggest.set(lineTemp);
                }

            });
        });

        /*
            遍历求出每一天的最大值和最大值这天的日期
         */
        dayList.forEach(time -> {
            AtomicReference<Double> cur = new AtomicReference<>(0.0);
            AtomicReference<Double> temp = new AtomicReference<>(0.0);
            AtomicReference<Double> leak = new AtomicReference<>(0.0);
            AtomicReference<Double> power = new AtomicReference<>(0.0);
            List<FireEqptAnalyzeDto> collect = list.stream().filter(fireEqptAnalyzeDto -> fireEqptAnalyzeDto.getAddTime().startsWith(time)).collect(Collectors.toList());

            collect.forEach(fireEqptAnalyzeDto -> {
                String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);

                Double cur1 = alarmMsgDouble.getCur();
                if(cur1>cur.get()){
                    cur.set(cur1);
                }
                Double pwr = alarmMsgDouble.getPwr();
                if(pwr>power.get()){
                    power.set(pwr);
                }
                Double leak_cur = alarmMsgDouble.getLeak_cur();
                if(leak_cur>leak.get()){
                    leak.set(leak_cur);
                }
                Double lineTemp = alarmMsgDouble.getLine_temp();
                if(lineTemp>temp.get()){
                    temp.set(lineTemp);
                }
            });
            curList.add(cur.get());
            powerList.add(power.get());
            leakList.add(leak.get());
            tempList.add(temp.get());

        });

        //每个月kwh之和
        List<String> monthList = getMonthLists(dataAnalyzeVo.getMonth());
        monthList.forEach(month->{
            final Double[] totalKwh = {0.0};
            List<FireEqptAnalyzeDto> collect = totalList.stream().filter(fireEqptAnalyzeDto -> fireEqptAnalyzeDto.getAddTime().startsWith(month)).collect(Collectors.toList());
            collect.forEach(fireEqptAnalyzeDto -> {
                String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);
                Double kwh = alarmMsgDouble.getKwh();
                totalKwh[0] += kwh;
            });
            kwhList.add(totalKwh[0]);
        });


        /*
            返回每个月峰值那天的每个小时的最大值
         */
        List<Double> curDayList4 = new ArrayList<>();
        List<Double> tempDayList4 = new ArrayList<>();
        List<Double> leakDayList4 = new ArrayList<>();
        List<Double> powerDayList4 = new ArrayList<>();


        List<String> hourCurLists = getHourLists(bigCur[0]);
        hourCurLists.forEach(hour->{

            AtomicReference<Double> curHour4 = new AtomicReference<>(0.0);
            AtomicReference<Double> tempHour4 = new AtomicReference<>(0.0);
            AtomicReference<Double> leakHour4 = new AtomicReference<>(0.0);
            AtomicReference<Double> powerHour4 = new AtomicReference<>(0.0);
            list.forEach(fireEqptAnalyzeDto -> {
                if(fireEqptAnalyzeDto.getAddTime().startsWith(hour)){
                    String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                    AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);

                    Double cur1 = alarmMsgDouble.getCur();
                    if(cur1>curHour4.get()){
                        curHour4.set(cur1);
                    }
                    Double pwr = alarmMsgDouble.getPwr();
                    if(pwr>powerHour4.get()){
                        powerHour4.set(pwr);
                    }
                    Double leak_cur = alarmMsgDouble.getLeak_cur();
                    if(leak_cur>leakHour4.get()){
                        leakHour4.set(leak_cur);
                    }
                    Double lineTemp = alarmMsgDouble.getLine_temp();
                    if(lineTemp>tempHour4.get()){
                        tempHour4.set(lineTemp);
                    }
                }
            });
            curDayList4.add(curHour4.get());
            powerDayList4.add(powerHour4.get());
            leakDayList4.add(leakHour4.get());
            tempDayList4.add(tempHour4.get());
        });

        List<Double> curDayList1 = new ArrayList<>();
        List<Double> tempDayList1 = new ArrayList<>();
        List<Double> leakDayList1 = new ArrayList<>();
        List<Double> powerDayList1 = new ArrayList<>();

        List<String> hourPowerLists = getHourLists(bigPower[0]);
        hourPowerLists.forEach(hour->{

            AtomicReference<Double> curHour1 = new AtomicReference<>(0.0);
            AtomicReference<Double> tempHour1 = new AtomicReference<>(0.0);
            AtomicReference<Double> leakHour1 = new AtomicReference<>(0.0);
            AtomicReference<Double> powerHour1 = new AtomicReference<>(0.0);

            list.forEach(fireEqptAnalyzeDto -> {
                if(fireEqptAnalyzeDto.getAddTime().startsWith(hour)){
                    String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                    AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);

                    Double cur1 = alarmMsgDouble.getCur();
                    if(cur1>curHour1.get()){
                        curHour1.set(cur1);
                    }
                    Double pwr = alarmMsgDouble.getPwr();
                    if(pwr>powerHour1.get()){
                        powerHour1.set(pwr);
                    }
                    Double leak_cur = alarmMsgDouble.getLeak_cur();
                    if(leak_cur>leakHour1.get()){
                        leakHour1.set(leak_cur);
                    }
                    Double lineTemp = alarmMsgDouble.getLine_temp();
                    if(lineTemp>tempHour1.get()){
                        tempHour1.set(lineTemp);
                    }
                }
            });
            curDayList1.add(curHour1.get());
            powerDayList1.add(powerHour1.get());
            leakDayList1.add(leakHour1.get());
            tempDayList1.add(tempHour1.get());
        });

        List<Double> curDayList2 = new ArrayList<>();
        List<Double> tempDayList2 = new ArrayList<>();
        List<Double> leakDayList2 = new ArrayList<>();
        List<Double> powerDayList2 = new ArrayList<>();


        List<String> hourLeakLists = getHourLists(bigLeak[0]);
        hourLeakLists.forEach(hour->{

            AtomicReference<Double> curHour2 = new AtomicReference<>(0.0);
            AtomicReference<Double> tempHour2 = new AtomicReference<>(0.0);
            AtomicReference<Double> leakHour2 = new AtomicReference<>(0.0);
            AtomicReference<Double> powerHour2 = new AtomicReference<>(0.0);
            list.forEach(fireEqptAnalyzeDto -> {
                if(fireEqptAnalyzeDto.getAddTime().startsWith(hour)){
                    String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                    AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);

                    Double cur1 = alarmMsgDouble.getCur();
                    if(cur1>curHour2.get()){
                        curHour2.set(cur1);
                    }
                    Double pwr = alarmMsgDouble.getPwr();
                    if(pwr>powerHour2.get()){
                        powerHour2.set(pwr);
                    }
                    Double leak_cur = alarmMsgDouble.getLeak_cur();
                    if(leak_cur>leakHour2.get()){
                        leakHour2.set(leak_cur);
                    }
                    Double lineTemp = alarmMsgDouble.getLine_temp();
                    if(lineTemp>tempHour2.get()){
                        tempHour2.set(lineTemp);
                    }
                }
            });
            curDayList2.add(curHour2.get());
            powerDayList2.add(powerHour2.get());
            leakDayList2.add(leakHour2.get());
            tempDayList2.add(tempHour2.get());
        });

        List<Double> curDayList3 = new ArrayList<>();
        List<Double> tempDayList3 = new ArrayList<>();
        List<Double> leakDayList3 = new ArrayList<>();
        List<Double> powerDayList3 = new ArrayList<>();
        List<String> hourTempLists = getHourLists(bigTemp[0]);

        hourTempLists.forEach(hour->{

            AtomicReference<Double> curHour3 = new AtomicReference<>(0.0);
            AtomicReference<Double> tempHour3 = new AtomicReference<>(0.0);
            AtomicReference<Double> leakHour3 = new AtomicReference<>(0.0);
            AtomicReference<Double> powerHour3 = new AtomicReference<>(0.0);
            list.forEach(fireEqptAnalyzeDto -> {
                if(fireEqptAnalyzeDto.getAddTime().startsWith(hour)){
                    String alarmMsg = fireEqptAnalyzeDto.getAlarmMsg();
                    AlarmMsgDouble alarmMsgDouble = JSONObject.parseObject(alarmMsg, AlarmMsgDouble.class);

                    Double cur1 = alarmMsgDouble.getCur();
                    if(cur1>curHour3.get()){
                        curHour3.set(cur1);
                    }
                    Double pwr = alarmMsgDouble.getPwr();
                    if(pwr>powerHour3.get()){
                        powerHour3.set(pwr);
                    }
                    Double leak_cur = alarmMsgDouble.getLeak_cur();
                    if(leak_cur>leakHour3.get()){
                        leakHour3.set(leak_cur);
                    }
                    Double lineTemp = alarmMsgDouble.getLine_temp();
                    if(lineTemp>tempHour3.get()){
                        tempHour3.set(lineTemp);
                    }
                }
            });
            curDayList3.add(curHour3.get());
            powerDayList3.add(powerHour3.get());
            leakDayList3.add(leakHour3.get());
            tempDayList3.add(tempHour3.get());
        });

        Analyze analyze = new Analyze()
                .setCur(curList)
                .setLeak(leakList)
                .setPower(powerList)
                .setLineTemp(tempList)
                .setCurDay4(curDayList4)
                .setPowerDay4(powerDayList4)
                .setLineTempDay4(tempDayList4)
                .setLeakDay4(leakDayList4)
                .setCurDay1(curDayList1)
                .setPowerDay1(powerDayList1)
                .setLineTempDay1(tempDayList1)
                .setLeakDay1(leakDayList1)
                .setCurDay2(curDayList2)
                .setPowerDay2(powerDayList2)
                .setLineTempDay2(tempDayList2)
                .setLeakDay2(leakDayList2)
                .setCurDay3(curDayList3)
                .setPowerDay3(powerDayList3)
                .setLineTempDay3(tempDayList3)
                .setLeakDay3(leakDayList3)
                .setKwh(kwhList);

        return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(4,analyze));
    }

    /**
     * 获取该时间段内以天为区分的list
     * @param startTime
     * @param endTime
     * @return
     */
    private List<String> getDayList(String startTime, String endTime) {
        List<String> betweenTime = new ArrayList<>();
        try {
            Date sdate= new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
            Date edate= new SimpleDateFormat("yyyy-MM-dd").parse(endTime);

            SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            int day = sCalendar.get(Calendar.DATE);
            sCalendar.set(year, month, day, 0, 0, 0);

            Calendar eCalendar = Calendar.getInstance();
            eCalendar.setTime(edate);
            year = eCalendar.get(Calendar.YEAR);
            month = eCalendar.get(Calendar.MONTH);
            day = eCalendar.get(Calendar.DATE);
            eCalendar.set(year, month, day, 0, 0, 0);

            while (sCalendar.before(eCalendar)) {
                String format = outformat.format(sCalendar.getTime());
                betweenTime.add(format);
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            //包括最后一天
            betweenTime.add(endTime);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return betweenTime;
    }

    /**
     * 获取该时间段以时为区分的list
     * @param date 某一天的日期
     * @return
     */
    private List<String> getHourLists(String date) {
        List<String> betweenTime = new ArrayList<>();

        try {
            Date sdate= new SimpleDateFormat("yyyy-MM-dd HH").parse(date);
            SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM-dd HH");

            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            int day = sCalendar.get(Calendar.DATE);
            sCalendar.set(year, month, day, 0, 0, 0);

            for (int i = 0; i < 24; i++) {
                String format = outformat.format(sCalendar.getTime());
                betweenTime.add(format);
                sCalendar.add(Calendar.HOUR, 1);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return betweenTime;
    }

    /**
     * 获取该时间段以月为区分的list
     * @return
     */
    private List<String> getMonthLists(int monthNum) {
        List<String> betweenTime = new ArrayList<>();
        List<String> list = new ArrayList<>();

        try {
            Date sdate= new SimpleDateFormat("yyyy-MM").parse(TimeUtil.getCurrentTime());
            SimpleDateFormat outformat = new SimpleDateFormat("yyyy-MM");

            Calendar sCalendar = Calendar.getInstance();
            sCalendar.setTime(sdate);
            int year = sCalendar.get(Calendar.YEAR);
            int month = sCalendar.get(Calendar.MONTH);
            sCalendar.set(year, month, 0, 0, 0, 0);

            for (int i = 0; i < monthNum; i++) {
                String format = outformat.format(sCalendar.getTime());
                betweenTime.add(format);
                sCalendar.add(Calendar.MONTH, -1);
            }
            for (int num = monthNum-1; num >= 0; num--) {
                list.add(betweenTime.get(num));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
