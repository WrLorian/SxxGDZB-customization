package com.kiwihouse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dto.EqptWarn;
import com.kiwihouse.dto.PerDayUsers;
import com.kiwihouse.dto.Statistics;
import com.kiwihouse.mapper.DataStatisticsMapper;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.DataStatisticsVo;

/**
 * @author yjzn
 * @date 2020-02-24-下午 4:04
 */
@Service
public class DataStatisticsService {

    @Autowired
    DataStatisticsMapper dataStatisticsMapper;

    /**
     * 查询数据总览信息
     * @param dataStatisticsVo 查询参数
     * @return
     */
    public ResultList queryInfo(DataStatisticsVo dataStatisticsVo, String adminId, boolean isTopMg) {

        HashMap<String, List<Integer>> map = queryFireAlm(dataStatisticsVo,adminId,isTopMg);       //各类火警告警信息对应设备数量
        HashMap<String, List<Integer>> map1 = querySmokeAlm(dataStatisticsVo, adminId,isTopMg);    //各类烟感告警信息对应设备数量
        int fireEqptNum = queryFireEqptNum(adminId,isTopMg);    //该管理员火警设备数量
        int smokeEqptNum = querySmokeEqptNum(adminId,isTopMg);  //该管理员烟感设备数量
        int allEqptNum = queryAllEqptNum(adminId,isTopMg);      //该管理员总设备数量
        int fireAlmEqptNum = queryFireAlmEqptNum(dataStatisticsVo, adminId,isTopMg);    //该管理员火警告警设备数量
        int smokeAlmEqptNum = querySmokeAlmEqptNum(dataStatisticsVo, adminId,isTopMg);  //查询烟感告警设备数量
//        List<Integer> perDayNewUserList = queryPerDayNewUser(dataStatisticsVo,adminId,isTopMg);     //查询每天用户新增数
//
//        List<Integer> allUserNumList = new ArrayList<>();
//        List<PerDayUsers> dayUserList = getDayUserList(dataStatisticsVo.getStartTime(), dataStatisticsVo.getEndTime());
//        dayUserList.forEach(perDayUsers -> {
//            String addTime = perDayUsers.getAddTime();
//            int allUserNum = queryAllNewUsers(addTime,adminId,isTopMg);   //截止当天用户总数
//            allUserNumList.add(allUserNum);
//        });

        int allUserNum = queryAllNewUsers(TimeUtil.getCurrentDate(),adminId,isTopMg);   //用户总数
        int newUserNum = dataStatisticsMapper.queryNewUser(TimeUtil.getCurrentDate());  //当天新用户数
        Statistics statistics = new Statistics()
                .setEqptNum(allEqptNum)
                .setFireNum(fireEqptNum)
                .setSmokeNum(smokeEqptNum)
                .setFireAlmEqptNum(fireAlmEqptNum)
                .setSmokeAlmEqptNum(smokeAlmEqptNum)
                .setFireWarnMap(map)
                .setSmokeWarnMap(map1)
                .setAllUserNum(allUserNum)
                .setNewUserNum(newUserNum)
                .setProcessedOrder(queryWorkOrder(0))       //未处理工单
                .setNotprocessedOrder(queryWorkOrder(1))    //已处理工单
                .setCancelledOrder(queryWorkOrder(2))       //已撤销工单
                .setProcessedCurrDayOrder(queryCurrDayWorkOrder(0))       //未处理工单
                .setNotprocessedCurrDayOrder(queryCurrDayWorkOrder(1))    //已处理工单
                .setCancelledCurrDayOrder(queryCurrDayWorkOrder(2));      //已撤销工单

        return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(1,statistics));

    }

    /**
     * 查询对应状态的总工单数量
     * @param status
     * @return
     */
    private int queryWorkOrder(int status){
        int fireOrderNum = dataStatisticsMapper.fireOrderNum(status);
        int smokeOrderNum = dataStatisticsMapper.smokeOrderNum(status);
        return fireOrderNum+smokeOrderNum;
    }

    /**
     * 查询对应状态当天的总工单数量
     * @param status
     * @return
     */
    private int queryCurrDayWorkOrder(int status){
        String currentDate = TimeUtil.getCurrentDate();
        int fireOrderNum = dataStatisticsMapper.fireCurrDayOrderNum(status,currentDate);
        int smokeOrderNum = dataStatisticsMapper.smokeCurrDayOrderNum(status,currentDate);
        return fireOrderNum+smokeOrderNum;
    }

    /**
     * 截止当天用户总数
     */
    private int queryAllNewUsers(String addTime, String adminId, boolean isTopMg) {
        HashMap<String,String> map = new HashMap<>();
        map.put("addTime",addTime);
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.queryAllNewUsers(map);
    }

    /**
     * 查询每天用户新增数
     */
    private List<Integer> queryPerDayNewUser(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg) {
        HashMap<String,String> map = new HashMap<>();
        String endTime = dataStatisticsVo.getEndTime();
        String startTime = dataStatisticsVo.getStartTime();
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }

        return handleUserList(dataStatisticsMapper.queryPerDayNewUser(map),startTime,endTime);
    }

    /**
     * 查询烟雾告警设备数量
     */
    private List<EqptWarn> querySmokeAlmNums(DataStatisticsVo dataStatisticsVo,String adminId,boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("almType","smoke");
        map.put("almThreshold","0.4");
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.querySmokeAlmNums(map);
    }
    /**
     * 查询信号告警设备数量
     */
    private List<EqptWarn> querySignalAlmNums(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("almType","signal");
        map.put("almThreshold","99");
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.querySmokeAlmNums(map);
    }
    /**
     * 查询湿度告警设备数量
     */
    private List<EqptWarn> queryHumidityAlmNums(DataStatisticsVo dataStatisticsVo,String adminId,boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("almType","humidity");
        map.put("almThreshold","1000.0");
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.querySmokeAlmNums(map);
    }
    /**
     * 查询温度告警设备数量
     */
    private List<EqptWarn> queryTemperatureAlmNums(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("almType","temperature");
        map.put("almThreshold","50");
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.querySmokeAlmNums(map);
    }

    /**
     * 查询告警设备数量
     */
    private int querySmokeAlmEqptNum(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.querySmokeAlmEqptNum(map);
    }

    private int queryFireAlmEqptNum(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("startTime",dataStatisticsVo.getStartTime());
        map.put("endTime",dataStatisticsVo.getEndTime());
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.queryFireAlmEqptNum(map);
    }
    /**
     * 查询所有设备
     */
    private int queryAllEqptNum(String adminId, boolean isTopMg) {
        HashMap<String,String> map = new HashMap<>();
        map.put("eqptType",null);
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.queryEqptNum(map);
    }

    /**
     * 查询该管理员的火警设备数量
     */
    private int queryFireEqptNum(String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("eqptType","0");
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.queryEqptNum(map);
    }
    /**
     * 查询该管理员的烟感设备数量
     */
    private int querySmokeEqptNum(String adminId, boolean isTopMg){
        HashMap<String,String> map = new HashMap<>();
        map.put("eqptType","1");
        if(isTopMg){
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return dataStatisticsMapper.queryEqptNum(map);
    }

    /**
     * 查询烟感设备各种告警信息数量
     *
     * 烟感设备告警信息阀值
     * smoke>=0.4
     * temperature>=50
     * humidity=1000.0
     * `signal`=99
     */
    private HashMap<String,List<Integer>> querySmokeAlm(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        String startTime = dataStatisticsVo.getStartTime();
        String endTime = dataStatisticsVo.getEndTime();

        HashMap<String,List<Integer>> map = new HashMap<>();

        map.put("smoke",handleList(querySmokeAlmNums(dataStatisticsVo,adminId,isTopMg),startTime,endTime));
        map.put("signal",handleList(querySignalAlmNums(dataStatisticsVo,adminId,isTopMg),startTime,endTime));
        map.put("humidity",handleList(queryHumidityAlmNums(dataStatisticsVo,adminId,isTopMg),startTime,endTime));
        map.put("temperature",handleList(queryTemperatureAlmNums(dataStatisticsVo,adminId,isTopMg),startTime,endTime));

        return map;
    }

    /**
     * 查询火警设备各种告警信息数量
     */
    private HashMap<String,List<Integer>> queryFireAlm(DataStatisticsVo dataStatisticsVo,String adminId, boolean isTopMg){
        String startTime = dataStatisticsVo.getStartTime();
        String endTime = dataStatisticsVo.getEndTime();

        HashMap<String,List<Integer>> map = new HashMap<>();

        if(isTopMg){
            dataStatisticsVo.setAdminId(null);
        }else {
            dataStatisticsVo.setAdminId(adminId);
        }
        map.put("totalFireAlmNum",handleList(dataStatisticsMapper.queryPreDayFireEqpt(dataStatisticsVo),startTime,endTime));
        map.put("cur",handleList(dataStatisticsMapper.queryCur(dataStatisticsVo),startTime,endTime));
        map.put("overload",handleList(dataStatisticsMapper.queryOverload(dataStatisticsVo),startTime,endTime));
        map.put("overVol",handleList(dataStatisticsMapper.queryOverVol(dataStatisticsVo),startTime,endTime));
        map.put("underVol",handleList(dataStatisticsMapper.queryUnderVol(dataStatisticsVo),startTime,endTime));
        map.put("leak",handleList(dataStatisticsMapper.queryLeak(dataStatisticsVo),startTime,endTime));
        map.put("temp",handleList(dataStatisticsMapper.queryTemp(dataStatisticsVo),startTime,endTime));

        return map;
    }

    /**
     *
     * @param fireList 生成返回的list，没有设备的天数默认默认为0
     * @param startTime
     * @param endTime
     */
    public List<Integer> handleList(List<EqptWarn> fireList, String startTime, String endTime){
        List<EqptWarn> list = getDayList(startTime,endTime);
        for (EqptWarn eqptWarn : fireList) {
            list.forEach(eqptWarn1 -> {
                if(eqptWarn1.getAddTime().equals(eqptWarn.getAddTime())){
                    eqptWarn1.setEqptNum(eqptWarn.getEqptNum());
                }
            });
        }
        List<Integer> resultList = new ArrayList<>();
        list.forEach(eqptWarn -> {
            resultList.add(eqptWarn.getEqptNum());
        });
        return resultList;
    }
    /**
     *
     * @param usersList 生成返回的list，没有用户的天数默认默认为0
     * @param startTime
     * @param endTime
     */
    public List<Integer> handleUserList(List<PerDayUsers> usersList, String startTime, String endTime){
        List<PerDayUsers> list = getDayUserList(startTime,endTime);
        for (PerDayUsers perDayUsers : usersList) {
            list.forEach(eqptWarn1 -> {
                if(eqptWarn1.getAddTime().equals(perDayUsers.getAddTime())){
                    eqptWarn1.setUserNum(perDayUsers.getUserNum());
                }
            });
        }
        List<Integer> resultList = new ArrayList<>();
        list.forEach(eqptWarn -> {
            resultList.add(eqptWarn.getUserNum());
        });
        return resultList;
    }
    /**
     * 获取该时间段内以天为区分的list
     * @param startTime
     * @param endTime
     * @return
     */
    private List<EqptWarn> getDayList(String startTime, String endTime) {
        List<EqptWarn> betweenTime = new ArrayList<>();
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
                EqptWarn eqptWarn = new EqptWarn();
                String format = outformat.format(sCalendar.getTime());
                eqptWarn.setEqptNum(0);
                eqptWarn.setAddTime(format);
                betweenTime.add(eqptWarn);
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            //包括最后一天
            EqptWarn eqptWarn = new EqptWarn();
            eqptWarn.setEqptNum(0);
            eqptWarn.setAddTime(endTime);
            betweenTime.add(eqptWarn);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return betweenTime;
    }
    /**
     * 获取该时间段内以天为区分的list
     * @param startTime
     * @param endTime
     * @return
     */
    private List<PerDayUsers> getDayUserList(String startTime, String endTime) {
        List<PerDayUsers> betweenTime = new ArrayList<>();
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
                PerDayUsers perDayUsers = new PerDayUsers();
                String format = outformat.format(sCalendar.getTime());
                perDayUsers.setUserNum(0);
                perDayUsers.setAddTime(format);
                betweenTime.add(perDayUsers);
                sCalendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            //包括最后一天
            PerDayUsers perDayUsers = new PerDayUsers();
            perDayUsers.setUserNum(0);
            perDayUsers.setAddTime(endTime);
            betweenTime.add(perDayUsers);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return betweenTime;
    }


}
