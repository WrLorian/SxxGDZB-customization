package com.kiwihouse.controller.group;

import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.controller.group.params.GroupListParams;
import com.kiwihouse.domain.vo.Response;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xin
 * @date 2020/7/19
 */
@RestController
@RequestMapping
public class GroupController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupController.class);


    @ApiOperation(value = "查询分组列表")
    @GetMapping("/list")
    @ResponseBody
    public Response list(GroupListParams params, HttpServletRequest request, HttpServletResponse response) {
        return new Response();
    }
}
