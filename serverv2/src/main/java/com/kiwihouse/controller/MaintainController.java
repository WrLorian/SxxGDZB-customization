package com.kiwihouse.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.MtInfoDto;
import com.kiwihouse.service.CheckAdminService;
import com.kiwihouse.service.MaintainService;
import com.kiwihouse.vo.entire.Log;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.MtInfoVo;
import com.kiwihouse.vo.kiwihouse.MtUpdateVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-1-4 10:48:29
 */
@Api(tags = "维修记录")
@RestController
@RequestMapping("/maintain")
public class MaintainController {
    private static final Logger logger = LoggerFactory.getLogger(MaintainController.class);

    @Autowired
    MaintainService maintainService;
    @Autowired
    CheckAdminService checkAdminService;

    @ApiOperation(value = "queryInfo",
            notes = "<br>@description: <b>查询维修信息信息</b></br>" +
                    "<br>@Date: <b>2020-1-4 13:45:00</b></br>",
            httpMethod = "GET")
    @ApiResponses({@ApiResponse(code = 0, message = "返回参数", response = MtInfoDto.class)})
    @GetMapping("/info")
    public ResultList queryInfo(@Validated MtInfoVo mtInfoVo, HttpServletRequest request){
        mtInfoVo.setPage((mtInfoVo.getPage()-1)*mtInfoVo.getLimit());
        checkAdminService.verifyAdminId(request.getHeader("dz-usr"),mtInfoVo);
        return maintainService.queryInfo(mtInfoVo);
    }

    @ApiOperation(value = "addInfo",
            notes = "<br>@description: <b>告警信息转工单</b></br>" +
                    "<br>@Date: <b>2020-1-4 17:15:40</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/info/{alarmId}/{eqptSn}/{mtType}")
    public Response addInfo(@PathVariable String alarmId,
                              @PathVariable String eqptSn,
                              @PathVariable String mtType,
                              HttpServletRequest request){
        logger.info("告警转工单>> {}",new Log().setIp(request.getRemoteAddr()).setParam(alarmId));
        Response resultList = maintainService.addInfo(alarmId,eqptSn,mtType);
        logger.info("返回参数<< {}",resultList);
        return resultList;
    }

    @ApiOperation(value = "updateInfo",
            notes = "<br>@description: <b>修改维修信息</b></br>" +
                    "<br>@Date: <b>2020-1-4 17:15:40</b></br>",
            httpMethod = "PUT")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PutMapping("/info")
    public Response updateInfo(@RequestBody @Validated MtUpdateVo mtUpdateVo,HttpServletRequest request){
        logger.info("修改维修信息>> {}",new Log().setIp(request.getRemoteAddr()).setParam(mtUpdateVo));
        if(!checkAdminService.isMtIdBelong2Admin(mtUpdateVo.getMtId(),request.getHeader("dz-usr"))){
            return new Response().Success(Code.PRIVILEGE_FAIL, Code.PRIVILEGE_FAIL.getMsg());
        }
        Response resultList = maintainService.updateInfo(mtUpdateVo);
        logger.info("返回参数<< {}",resultList);
        return resultList;
    }

}
