package com.kiwihouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.common.bean.AlmSta;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.MtSta;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dao.mapper.AlarmMapper;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.MtInfoDto;
import com.kiwihouse.dto.MtSmokeInfoDto;
import com.kiwihouse.dto.WarmMsgValue;
import com.kiwihouse.dto.WarnMsgDto;
import com.kiwihouse.mapper.MaintainMapper;
import com.kiwihouse.service.MaintainService;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.MtInfoVo;
import com.kiwihouse.vo.kiwihouse.MtUpdateVo;

@Service
public class MaintainServiceImpl implements MaintainService{

	 	@Autowired
	    MaintainMapper maintainMapper;

	 	@Autowired
	 	AlarmMapper AlarmMapper;
	    /**
	     * 查询:告警信息+维修记录
	     * @param mtInfoVo 查询参数
	     * @return 维修信息结果集
	     */
	 	@Override
	    public ResultList queryInfo(MtInfoVo mtInfoVo) {

	        String mtStatus = mtInfoVo.getMtStatus();
	        if("9".equals(mtStatus)){
	            mtInfoVo.setMtStatus("noCancel");
	        }

	        if("2".equals(mtInfoVo.getMtType())){
	            //查询烟感设备工单
	            List<MtSmokeInfoDto> list = maintainMapper.querySmokeInfo(mtInfoVo);
	            if(list.isEmpty()){
	                return new ResultList(Code.QUERY_NULL.getCode(),Code.QUERY_NULL.getMsg(),null);
	            }else{
	                Integer row = maintainMapper.querySmokeInfoRow(mtInfoVo);
	                if(row==0){
	                    return new ResultList(Code.EXECUTION_ERROR.getCode(),Code.EXECUTION_ERROR.getMsg(),null);
	                }else{
	                    list.forEach(mtSmokeInfoDto -> mtSmokeInfoDto.setMtType("2"));
	                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
	                }
	            }

	        }else{
	            //查询用电设备工单
	            List<MtInfoDto> list = maintainMapper.queryInfo(mtInfoVo);
	            if(list.isEmpty()){
	                return new ResultList(Code.QUERY_NULL.getCode(),Code.QUERY_NULL.getMsg(),null);
	            }else{
	                Integer row = maintainMapper.queryInfoRow(mtInfoVo);
	                if(row==0){
	                    return new ResultList(Code.EXECUTION_ERROR.getCode(),Code.EXECUTION_ERROR.getMsg(),null);
	                }else{
	                    list.forEach(mtInfoDto -> {
	                        mtInfoDto.setMtType("1");

	                        String alarmMsg = mtInfoDto.getAlarmMsg();
	                        WarnMsgDto warnMsgDto = JSONObject.parseObject(alarmMsg, WarnMsgDto.class);
	                        HashMap<String,Object> almMap= new HashMap<>();
	                        String cur = warnMsgDto.getCur();
	                        if(!"0".equals(cur)){
	                            String[] split = cur.split("-");
	                            if(2==split.length) {
	                                WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                warmMsgValue.setMsg("过流告警");
	                                warmMsgValue.setValue(split[1]+"A");
	                                almMap.put("cur", JSONObject.toJSONString(warmMsgValue));
	                            }
	                        }
	                        String temp = warnMsgDto.getTemp();
	                        if(!"0".equals(temp)){
	                            String[] split = temp.split("-");
	                            if(2==split.length) {
	                                WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                warmMsgValue.setMsg("线温告警");
	                                warmMsgValue.setValue(split[1]+"℃");
	                                almMap.put("temp", JSONObject.toJSONString(warmMsgValue));
	                            }
	                        }
	                        String leak = warnMsgDto.getLeak();
	                        if(!"0".equals(leak)){
	                            String[] split = leak.split("-");
	                            if(2==split.length) {
	                                WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                warmMsgValue.setMsg("漏电流告警");
	                                warmMsgValue.setValue(split[1]+"mA");
	                                almMap.put("leak", JSONObject.toJSONString(warmMsgValue));
	                            }
	                        }
	                        String overload = warnMsgDto.getOverload();
	                        if(!"0".equals(overload)){
	                            String[] split = overload.split("-");
	                            if(2==split.length) {
	                                WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                warmMsgValue.setMsg("过载告警");
	                                warmMsgValue.setValue(split[1]+"W");
	                                almMap.put("overload", JSONObject.toJSONString(warmMsgValue));
	                            }
	                        }
	                        String vol = warnMsgDto.getVol();
	                        if(StringUtils.isNotBlank(vol)){
	                            if(vol.startsWith("1")){
	                                String[] split = vol.split("-");
	                                if(2==split.length) {
	                                    WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                    warmMsgValue.setMsg("过压告警");
	                                    warmMsgValue.setValue(split[1]+"V");
	                                    almMap.put("vol", JSONObject.toJSONString(warmMsgValue));
	                                }
	                            }else if(vol.startsWith("2")){
	                                String[] split = vol.split("-");
	                                if(2==split.length) {
	                                    WarmMsgValue warmMsgValue = new WarmMsgValue();
	                                    warmMsgValue.setMsg("欠压告警");
	                                    warmMsgValue.setValue(split[1]+"V");
	                                    almMap.put("vol", JSONObject.toJSONString(warmMsgValue));

	                                }
	                            }
	                        }
	                        mtInfoDto.setAlmValue(almMap);
	                    });
	                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
	                }
	            }
	        }
	    }

	    /**
	     * 告警信息转为工单
	     * @param alarmId 录入信息
	     * @return 成功与否
	     */
	    @Transactional(rollbackFor = Exception.class)
	    @Override
	    public Response addInfo(String alarmId,String eqptSn,String mtType) {

	        Integer almRow;
	        Integer row;
	        if("1".equals(mtType)){
	            //用电设备
	            almRow = AlarmMapper.updateAlmSta(alarmId, AlmSta.TO_ORDER.getCode());
	            row = maintainMapper.addInfo(alarmId, eqptSn, TimeUtil.getCurrentTime());
	        }else if("2".equals(mtType)){
	            //烟感设备
	            almRow = maintainMapper.updateSmokeAlmSta(alarmId, AlmSta.TO_ORDER.getCode());
	            row = maintainMapper.addSmokeInfo(alarmId, eqptSn, TimeUtil.getCurrentTime());
	        }else{
	        	return new Response().Fail(Code.PARAM_FORMAT_ERROR, Code.PARAM_FORMAT_ERROR.getMsg());
	        }

	        if (row == 0 && almRow == 0) {
	        	return new Response().Fail(Code.ADD_FAIL, Code.ADD_FAIL.getMsg());
	        } else {
	        	return new Response().Success(Code.ADD_SUCCESS, Code.ADD_SUCCESS.getMsg());
	        }
	    }

	    /**
	     * 维修人员处理告警结束，录入信息并修改告警处理状态
	     * 根据状态修改告警信息对应的状态
	     * @param mtUpdateVo 更新参数
	     * @return 成功与否
	     */
	    @Transactional(rollbackFor = Exception.class)
	    @Override
	    public Response updateInfo(MtUpdateVo mtUpdateVo) {
	    	System.out.println("------------------->");
	        String mtType = mtUpdateVo.getMtType();
	        if("1".equals(mtType)) {
	            Integer mtRow = maintainMapper.updateMtInfo(mtUpdateVo);

	            // 默认为1，则当不修改状态时，也可以返回处理成功
	            Integer almRow = 1;
	            String mtStatus = mtUpdateVo.getMtStatus();
	            if (MtSta.UNPROCESSED.getCode().equals(mtStatus)) {
	                //未维修 对应 已转为工单
	                almRow = AlarmMapper.updateAlmSta(mtUpdateVo.getAlarmId(), AlmSta.TO_ORDER.getCode());
	            } else if (MtSta.PROCESSED.getCode().equals(mtStatus)) {
	                //已维修 对应 已处理
	                almRow = AlarmMapper.updateAlmSta(mtUpdateVo.getAlarmId(), AlmSta.PROCESSED.getCode());
	            } else if (MtSta.CANCEL.getCode().equals(mtStatus)) {
	                //撤销订单 对应 错误告警
	                almRow = AlarmMapper.updateAlmSta(mtUpdateVo.getAlarmId(), AlmSta.ERROR.getCode());
	            }

	            if (almRow == 1 && mtRow == 1) {
	            	return new Response().Success(Code.UPDATE_SUCCESS, Code.UPDATE_SUCCESS.getMsg());
	            } else {
	            	return new Response().Fail(Code.UPDATE_FAIL, Code.UPDATE_FAIL.getMsg());
	            }
	        }else if("2".equals(mtType)){
	            Integer mtRow = maintainMapper.updateMtSmokeInfo(mtUpdateVo);

	            // 默认为1，则当不修改状态时，也可以返回处理成功
	            Integer almRow = 1;
	            String mtStatus = mtUpdateVo.getMtStatus();
	            if (MtSta.UNPROCESSED.getCode().equals(mtStatus)) {
	                //未维修 对应 已转为工单
	                almRow = maintainMapper.updateSmokeAlmSta(mtUpdateVo.getAlarmId(), AlmSta.TO_ORDER.getCode());
	            } else if (MtSta.PROCESSED.getCode().equals(mtStatus)) {
	                //已维修 对应 已处理
	                almRow = maintainMapper.updateSmokeAlmSta(mtUpdateVo.getAlarmId(), AlmSta.PROCESSED.getCode());
	            } else if (MtSta.CANCEL.getCode().equals(mtStatus)) {
	                //撤销订单 对应 错误告警
	                almRow = maintainMapper.updateSmokeAlmSta(mtUpdateVo.getAlarmId(), AlmSta.ERROR.getCode());
	            }

	            if (almRow == 1 && mtRow == 1) {
	            	return new Response().Success(Code.UPDATE_SUCCESS, Code.UPDATE_SUCCESS.getMsg());
	            } else {
	            	return new Response().Fail(Code.UPDATE_FAIL, Code.UPDATE_FAIL.getMsg());
	            }
	        }else{
	        	return new Response().Success(Code.ADD_SUCCESS, Code.ADD_SUCCESS.getMsg());
	        }

	    }
}
