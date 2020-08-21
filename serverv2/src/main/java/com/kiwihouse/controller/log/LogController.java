package com.kiwihouse.controller.log;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.entity.AuthAccountLog;
import com.kiwihouse.dao.entity.AuthOperationLog;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.AccountLogService;
import com.kiwihouse.service.OperationLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author tomsun28
 * @date 12:20 2018/4/22
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    OperationLogService operationLogService;

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取日志记录", httpMethod = "GET")
    @RequestMapping("/accountLog/{currentPage}/{pageSize}")
    public Response getAccountLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize ) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthAccountLog> accountLogs = accountLogService.getAccountLogList();
        PageInfo pageInfo = new PageInfo(accountLogs);
        return new Response().Success(6666, "return accountLogs success").addData("data",pageInfo);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户操作api日志列表", httpMethod = "GET")
    @RequestMapping("/operationLog/{currentPage}/{pageSize}")
    public Response getOperationLogList(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<AuthOperationLog> authOperationLogs = operationLogService.getOperationList();
        PageInfo pageInfo = new PageInfo(authOperationLogs);
        return new Response().Success(6666, "return operationLogList success").addData("data", pageInfo);
    }
}
