package com.kiwihouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.CronUtil;
import com.kiwihouse.common.utils.QuartzManager;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dao.mapper.KwhMgMapper;
import com.kiwihouse.domain.vo.AuthRoleMenuDetails;
import com.kiwihouse.dto.AlarmMsg;
import com.kiwihouse.dto.ElectricEnergy;
import com.kiwihouse.dto.MeterNowDto;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.KwhMgService;
import com.kiwihouse.service.PrivilegeService;
import com.kiwihouse.service.schedule.MeterKwh;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
@Service
public class KwhMgServiceImpl implements KwhMgService{

	@Autowired
    KwhMgMapper kwhMgMapper;
    @Autowired
    CheckAdminService checkAdminService;
    @Autowired
    PrivilegeService privilegeService;

    public static String JOB_NAME = "job";
    public static String TRIGGER_NAME = "trigger";
    public static String JOB_GROUP_NAME = "YJZN_JOB_GROUP";
    public static String TRIGGER_GROUP_NAME = "YJZN_JOB_GROUP";
    
	
	/**
     * 添加抄表任务
     * @param schedule 每年哪些月的某一天
     * @param tod 某一天的具体时间
     * @param adminId 管理员ID
     * @param groupId 要抄表的组
     * @return 是否抄表成功
     */
    @Override
    public ResultList meterInfo(String schedule, String tod,String groupId ,String adminId) {
        //first 校验周期
        String[] split = schedule.split("-");
        if (split.length != 2) { //校验cron-月份和日期
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "schedule(" + schedule + ")不正确", null);
        } else if (!"L".equals(split[1]) && !"*".equals(split[1])) {
            try {
                int integer = Integer.parseInt(split[1]);
                if (integer < 0 || integer > 28) {
                    return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "参数:" + schedule + " 不正确,日期不能大于28", null);
                }
            } catch (NumberFormatException e) {
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "参数:" + schedule + " 不正确", null);
            }
        }

        String[] split1 = tod.split(":");
        if (split1.length != 3) {   //校验日期
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "tod(" + tod + ")不正确", null);
        }

        String cron = CronUtil.ToCron(schedule, tod);   //根据时间,生成cron表达式
        String[] split2 = groupId.split("-");
        for (String groupIds : split2) {
            kwhMgMapper.updateGroup(groupIds, cron);
            QuartzManager.addJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME,
                    adminId, groupIds, MeterKwh.class, cron);    //添加quartz job

        }
        return new ResultList(Code.ADD_SUCCESS.getCode(), "抄表周期为:" + cron, null);
    }

    /**
     * 暂停某个小区的抄表任务
     * @param adminId 管理员Id
     * @param groupId 组id
     */
    @Override
    public ResultList stopInfo(String groupId,String roleId) {
        kwhMgMapper.updateGroup(groupId, "ncron");
        QuartzManager.removeJob(JOB_NAME, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, groupId, roleId);
        return new ResultList(Code.DELETE_SUCCESS.getCode(), "结束抄表任务", null);
    }

    /**
     *
     * @param groupId
     * @param eqptSn
     * @return
     */
    @Override
    public Map<String, Object> queryInfo(String groupId, String eqptSn, Integer page, Integer limit, HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	HashMap<String,Object> maps = new HashMap<>();
        maps.put("groupId",groupId);
        maps.put("eqptSn",eqptSn);
        String roleId = request.getHeader("dz-usr");
        maps.put("roleId",roleId);
//      if(privilegeService.isTopMg(adminId)){
//      map.put("adminId",null);
//  }else{
//      map.put("adminId", adminId);
//  }
    	map.put("count", kwhMgMapper.queryInfoRow(maps));
		List<ElectricEnergy> list = new ArrayList<ElectricEnergy>();
		if (page != null) {
			 maps.put("page",(page-1)*limit);
	         maps.put("limit",limit);
			 list= kwhMgMapper.queryInfo(maps);
		} else {
	         maps.put("limit",null);
	         list= kwhMgMapper.queryInfo(maps);
		}
		map.put("data", list);
		return map;
    }

    /**
     * 立即抄表，返回eqptSn对应数据为空的集合
     * @param list 设备序列号集合
     * @return
     */
    @Override
    public ResultList meterNow(List<String> list) {

        List<String> blankEqptSnList = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        String currentTime = TimeUtil.getCurrentTime();

        for (String eqptSn : list) {
            if(StringUtils.isNotBlank(eqptSn)){
                MeterNowDto meterNowDto =kwhMgMapper.queryMeterInfo(eqptSn);    //查询最新一次设备上报数据
                if(null!=meterNowDto){
                    String alarmMsg = meterNowDto.getAlarmMsg();
                    AlarmMsg kwhDto = JSONObject.parseObject(alarmMsg, AlarmMsg.class);
                    meterNowDto.setKwh(kwhDto.getKwh());
                    meterNowDto.setAddTime(currentTime);
                    //获取抄表批次
                    Integer lastTimes = kwhMgMapper.selectTimesByEqptSn(eqptSn);
                    //设置抄表批次
                    if(null == lastTimes){
                        meterNowDto.setTimes("1");
                    }else{
                        meterNowDto.setTimes(String.valueOf(lastTimes+1));
                    }

                    Integer row = kwhMgMapper.insertMeterInfo(meterNowDto);     //录入抄表记录
                    if(1!=row){
                        return new ResultList(Code.ADD_FAIL.getCode(),"抄表失败",null);
                    }
                }else{
                    blankEqptSnList.add(eqptSn);
                }
            }

        }
        map.put("emptyList",blankEqptSnList);
        return new ResultList(Code.ADD_SUCCESS.getCode(),"抄表结束",new Result<>(blankEqptSnList.size(),map));
    }

}
