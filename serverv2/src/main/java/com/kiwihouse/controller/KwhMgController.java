package com.kiwihouse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.domain.vo.AuthRoleResourceVo;
import com.kiwihouse.dto.ElectricEnergy;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.KwhMgService;
import com.kiwihouse.vo.entire.ResultList;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-1-14 14:17:46
 */
@Api(tags = "电能管理")
@RestController
@RequestMapping("/kwhmg")
public class KwhMgController extends BaseController{

    @Autowired
    KwhMgService kwhMgService;
    @Autowired
    CheckAdminService checkAdminService;

    @ApiOperation(value = "meterInfo:开始抄表,从当前时间开始",
            notes = "<br>@description: <b>开始抄表,从当前时间开始</b></br>" +
                    "<br>@Date: <b>2020-1-15 16:54:44</b></br>",
            httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "周期(那几个月的某天,其中具体日期不可超过28。格式为:1,2,3-15 <==>1、2、3的15号,日期可为L代表最后一天,若为每个月则用*代替)",name = "schedule",required = true,paramType = "path"),
            @ApiImplicitParam(value = "区域ID(多个区域格式为:1-2-3-4)",name = "groupId",required = true,paramType = "path"),
            @ApiImplicitParam(value = "Time Of Day(每天的时间)",name = "tod",required = true,paramType = "path")
    })
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/start/{schedule}/{tod}/{groupId}")          //tod <==> time of day
    public ResultList meterInfo(@PathVariable(required = true)String schedule,
                                @PathVariable(required = true)String tod,
                                @PathVariable(required = true)String groupId,
                                HttpServletRequest request){
        String adminId = request.getHeader("dz-usr");
        if(!checkAdminService.isGroupIdBelong2Admin(groupId,adminId)) {
            return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "不能操作不属于自己的组", null);
        }
        return kwhMgService.meterInfo(schedule,tod,groupId, adminId);
    }

    @ApiOperation(value = "meterNow:立即抄表",
            notes = "<br>@description: <b>可以选择一个或多个设备抄表,参数为eqptSn集合</b></br>" +
                    "<br>@Date: <b>2020-1-15 16:54:44</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：code,msg,result:eqptSn对应数据为空的SN号集合"))
    @PostMapping("/meterNow")
    public ResultList meterNow(@RequestBody List<String> list, HttpServletRequest request){
        if(list.isEmpty()){
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"参数列表不能为空",null);
        }
        return kwhMgService.meterNow(list);
    }

    @ApiOperation(value = "stopInfo:停止抄表业务",
            notes = "<br>@description: <b>停止抄表业务</b></br>" +
                    "<br>@Date: <b>2020-1-15 17:31:54</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/stop/{groupId}")
    public ResultList stopInfo(@PathVariable String groupId, HttpServletRequest request){
        String roleId = request.getHeader("dz-usr");
//        if(!checkAdminService.isGroupIdBelong2Admin(groupId,adminId)) {
//            return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "不能操作不属于自己的组", null);
//        }
        return kwhMgService.stopInfo(groupId, roleId);
    }

    @ApiOperation(value = "queryInfo:查询抄表记录",
            notes = "<br>@description: <b>查询抄表记录</b></br>" +
                    "<br>@Date: <b>2020-2-25 10:52:32</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "回调：抄表参数", response = ElectricEnergy.class)})
    @GetMapping("/info")
    public Map<String, Object> queryInfo(String groupId,
                                String eqptSn,
                                 Integer page,
                                 Integer limit,
                                HttpServletRequest request){
    	try {
    		map =   kwhMgService.queryInfo(groupId,eqptSn,page,limit,request);
    		map.put("code", 0);
    		map.put("msg",Code.QUERY_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
        return map;
       
    }

}
