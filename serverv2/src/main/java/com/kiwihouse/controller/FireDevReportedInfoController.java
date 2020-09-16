package com.kiwihouse.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.DataType;
import com.kiwihouse.common.bean.EqptTypeSta;
import com.kiwihouse.common.bean.UserInfo;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.mapper.EquipmentMapper;
import com.kiwihouse.domain.vo.AuthRoleResourceVo;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.AlarmEqptDto;
import com.kiwihouse.dto.EqptInfoDto;
import com.kiwihouse.dto.ReportedDto;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.DevInfoService;
import com.kiwihouse.service.ReportedInfoService;
import com.kiwihouse.service.ThreePhaseService;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.AlmQueryVo;
import com.kiwihouse.vo.kiwihouse.QueryPwrVo;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;
import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-1-3 15:37:06
 */
@RestController
@RequestMapping("/receive")
@Api(tags = "火警上报信息")
public class FireDevReportedInfoController extends BaseController{

    @Autowired
    ReportedInfoService reportedInfoService;
    @Autowired
    CheckAdminService checkAdminService;
    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    ThreePhaseService threePhaseService;
    @Autowired
    DevInfoService devInfoService;
    @ApiOperation(value = "queryInfo",
            notes = "<br>@description: <b>查询设备上报信息</b></br>" +
                    "<br>@Date: <b>2020-1-3 15:36:22</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = ReportedDto.class)})
    @GetMapping(value = "/info")
    public Response queryInfo(@Validated ReportedQueryVo reportedQueryVo, HttpServletRequest request) {
        //checkAdminService.verifyAdminId(request.getHeader("dz-usr"), reportedQueryVo);
    	reportedQueryVo.setUserId(request.getHeader("dz-usr"));
        EqptInfoDto eqptInfo = equipmentMapper.queryInfoByImei(reportedQueryVo.getImei());
        if (eqptInfo.getEqptType().equals(EqptTypeSta.SX)) {
            return threePhaseService.getLastStatus(reportedQueryVo);
        } else {
        	if(DataType.FIRE_REPORT_cl.equals(reportedQueryVo.getAlarmType())) {
        		return devInfoService.selectDevByNewTime(reportedQueryVo.getImei());
        	}else if(DataType.FIRE_REPORT_alm.equals(reportedQueryVo.getAlarmType())) {
        		//火警设备告警信息
        		return reportedInfoService.devAlarmInfo(reportedQueryVo);
        	}else {
        		//火警设备运行信息
        		return reportedInfoService.devRunInfo(reportedQueryVo);
        	}
        }
    }

    @ApiOperation(value = "queryAlmInfo",
            notes = "<br>@description: <b>查询设备告警信息</b></br>" +
                    "<br>@Date: <b>2020-1-9 19:57:11</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = AlarmEqptDto.class)})
    @GetMapping(value = "/alarm/info")
    public  Map<String, Object>  queryAlmInfo(@Validated AlmQueryVo almQueryVo, HttpServletRequest request) {
        //checkAdminService.verifyAdminId(request.getHeader("dz-usr"), almQueryVo);
    	ResultList resultList =  reportedInfoService.queryAlmInfo(almQueryVo);
    	if(resultList.getResult() != null) {
    		map.put("data", resultList.getResult().getData());
    		map.put("code", 0);
    		map.put("count", resultList.getResult().getRow());
    		map.put("msg",Code.QUERY_SUCCESS);
    	}else {
    		return putMsgToJsonString(0, Code.QUERY_NULL.getMsg(), 0, null);
    	}
    	try {
    		
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
        return map;
    }

    @ApiOperation(value = "queryLatestAlmInfo",
            notes = "<br>@description: <b>查询过去15内的告警数据</b></br>" +
                    "<br>@Date: <b>2020-3-25 09:44:13</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = AlarmEqptDto.class)})
    @GetMapping(value = "/latestalarm/info/{second}/{page}/{limit}")
    public Map<String, Object> queryLatestAlmInfo(@PathVariable int page, @PathVariable int limit, @PathVariable int second, HttpServletRequest request) {

        String currentTime = TimeUtil.getCurrentTime();
        String passSecTime = TimeUtil.getPassSecTime(currentTime, -second);

        AlmQueryVo almQueryVo = new AlmQueryVo()
                .setStartTime(passSecTime)
                .setEndTime(currentTime)
                .setAlarmStatus("0")
                .setPage(page)
                .setLimit(limit);
        return queryAlmInfo(almQueryVo, request);
    }

    @ApiOperation(value = "queryPwr",
            notes = "<br>@description: <b>查询设备上报功率数据</b></br>" +
                    "<br>@Date: <b>2020-3-30 13:32:52</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = ReportedDto.class)})
    @GetMapping(value = "/pwr/info")
    public Response queryPwr(@Validated QueryPwrVo queryPwrVo, HttpServletRequest request) throws ParseException {
        //checkAdminService.verifyAdminId(request.getHeader("dz-usr"), queryPwrVo);

        EqptInfoDto eqptInfo = equipmentMapper.queryInfoByImei(queryPwrVo.getImei());
        if (eqptInfo.getEqptType().equals(EqptTypeSta.SX)) {
            ThreePhaseVo tpv = new ThreePhaseVo();
            tpv.setImei(eqptInfo.getImei());
            tpv.setDataType(queryPwrVo.getType());
            tpv.setBeginTime(queryPwrVo.getStartTime());
            tpv.setEndTime(queryPwrVo.getEndTime());
            return threePhaseService.getMaxPowerList(tpv);
        } else {
            return reportedInfoService.queryPwr(queryPwrVo);
        }

    }

}
