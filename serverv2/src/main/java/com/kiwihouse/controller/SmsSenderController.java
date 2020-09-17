package com.kiwihouse.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.service.SendSmsService;
import com.kiwihouse.vo.entire.Log;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.SendSmsVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author yjzn
 * @date 2020-2-18 15:54:54
 */
@Api(tags = "短信发送接口")
@RestController
@RequestMapping("/sms")
public class SmsSenderController {
    private final Logger logger = LoggerFactory.getLogger(SmsSenderController.class);

    @Autowired
    SendSmsService sendSmsService;

    @ApiOperation(value = "sendSms",
            notes = "<br>@description: <b></b></br>" +
                    "<br>@Date: <b>2020-3-10 14:40:56</b></br>",
            httpMethod = "POST")
    @ApiResponses(@ApiResponse(code = 0,message ="回调参数：只有code和msg,无具体数据result"))
    @PostMapping("/sender")
    public ResultList sendSms(@RequestBody @Validated List<SendSmsVo> list, HttpServletRequest request){
        logger.info("发送短信 >> {}",new Log().setIp(request.getRemoteAddr()).setMsg("发送人ID:"+request.getHeader("dz-usr")).setParam(list));
        return sendSmsService.SendSms(list);
    }
}
