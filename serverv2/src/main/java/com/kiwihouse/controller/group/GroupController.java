package com.kiwihouse.controller.group;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.GroupDto;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.GroupService;
import com.kiwihouse.vo.entire.Log;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.GroupAddVo;
import com.kiwihouse.vo.kiwihouse.GroupQueryVo;
import com.kiwihouse.vo.kiwihouse.GroupUpdateVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-03-05-下午 3:43
 */
@RestController
@RequestMapping("/group")
@Api(tags = "分组管理")
public class GroupController extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    GroupService groupService;
    @Autowired
    CheckAdminService checkAdminService;

    @ApiOperation(value = "queryInfo",
            notes = "<br>@description: <b>查询分组信息</b></br>" +
                    "<br>@Date: <b>2020-3-5 15:45:51</b></br>",
            httpMethod = "GET")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数",response = GroupDto.class))
    @GetMapping("/info")
    public Map<String, Object> queryInfo(@Validated GroupQueryVo groupQueryVo, HttpServletRequest request){
    	try {
    		if(groupQueryVo==null) {
    			groupQueryVo = new GroupQueryVo();
    		}
    		map = groupService.queryInfo(groupQueryVo);
    		map.put("code", 0);
    		map.put("msg",Code.QUERY_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
        return map;
    }

    @ApiOperation(value = "selectOneInfo",
            notes = "<br>@description: <b>查询一条分组信息</b></br>" +
                    "<br>@Date: <b>2020-3-5 16:39:06</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/selectOneInfo")
    public Response selectOneInfo(@RequestBody @Validated GroupQueryVo groupQueryVo, HttpServletRequest request){
        logger.info("录入区域信息>> {}",new Log().setIp(request.getRemoteAddr()).setMsg("添加区域信息").setParam(groupQueryVo.toString()));
        GroupDto gv  = groupService.selectOneInfo(groupQueryVo);
        return new Response().Success(6666,"return a GroupDto success").addData("GroupDto",gv);
    }
    
    @ApiOperation(value = "addInfo",
            notes = "<br>@description: <b>添加分组信息</b></br>" +
                    "<br>@Date: <b>2020-3-5 16:39:06</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/info")
    public Response addInfo(@RequestBody @Validated GroupAddVo groupAddVo, HttpServletRequest request){
        logger.info("录入区域信息>> {}",new Log().setIp(request.getRemoteAddr()).setMsg("添加区域信息").setParam(groupAddVo.toString()));
//        String adminId = request.getHeader("dz-usr");
//        groupAddVo.setAdminId(adminId);
        return groupService.addInfo(groupAddVo);
    }

    @ApiOperation(value = "updateInfo",
            notes = "<br>@description: <b>修改分组信息</b></br>" +
                    "<br>@Date: <b>2020-3-11 10:55:54</b></br>",
            httpMethod = "PUT")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PutMapping("/info")
    public ResultList updateInfo(@RequestBody @Validated GroupUpdateVo groupUpdateVo, HttpServletRequest request){
        logger.info("修改分组信息>> {}",new Log().setIp(request.getRemoteAddr()).setMsg("修改分组信息").setParam(groupUpdateVo.toString()));
        groupUpdateVo.setDoAdminId(request.getHeader("dz-usr"));
        return groupService.updateInfo(groupUpdateVo);
    }

    @ApiOperation(value = "deleteInfo",
            notes = "<br>@description: <b>删除分组</b></br>" +
                    "<br>@Date: <b>2020-3-5 16:39:06</b></br>",
            httpMethod = "DELETE")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @DeleteMapping("/info/{groupId}")
    public ResultList deleteInfo(@PathVariable String groupId, HttpServletRequest request){
        logger.info("删除分组>> {}",new Log().setIp(request.getRemoteAddr()).setMsg("删除空分组").setParam(groupId));
        String roleId = request.getHeader("dz-roleId");
        String eqptIds = request.getHeader("dz-eqptIds");
        return groupService.deleteInfo(groupId,Integer.valueOf(roleId),eqptIds);
    }

}
