package com.kiwihouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.service.CommandIssueService;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.CommandVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-01-03-上午 11:17
 */
@Validated
@Api(tags = "操作设备")
@RestController
@RequestMapping("/onenet")
public class CommandIssueController {

    @Autowired
    CommandIssueService commandIssueService;

    @ApiOperation(value = "commandIssuedList",
            notes = "<br>@description: <b>设备批量命令下发，异步操作</b></br>" +
                    "<br>@Date: <b>2020-1-13 11:37:29</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：code、msg、result:onenet平台响应信息"))
    @PostMapping("/issuedList")
    public ResultList commandIssuedList(@RequestBody @Valid List<CommandVo> list, HttpServletRequest request){
        return commandIssueService.commandsIssued(list,request);
    }

    @ApiOperation(value = "registerToOnenet",
            notes = "<br>@description: <b>设备批量注册到onenet平台</b></br>" +
                    "<br>@Return: <b>注册到onenet平台失败的设备信息,返回map中key为设备SN号,value为错误原因</b></br>"+
                    "<br>@Date: <b>2020-1-3 15:00:33</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：code、msg、result:注册到onenet平台失败的设备序列号以及onenet平台返回的错误信息"))
    @PostMapping("/register/device")
    public ResultList registerToOnenet(@RequestBody @Valid List<com.kiwihouse.vo.kiwihouse.RegisterDevice> list,HttpServletRequest request){
        return commandIssueService.registerToOnenetList(list,request);
    }
}
