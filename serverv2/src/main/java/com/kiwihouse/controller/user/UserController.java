package com.kiwihouse.controller.user;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.UserService;
import com.kiwihouse.util.JsonWebTokenUtil;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 用户相关操作
 *
 * @author tomsun28
 * @date 21:05 2018/3/17
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @ApiOperation(value = "获取对应用户角色", notes = "GET根据用户的appId获取对应用户的角色")
    @GetMapping("/role/{appId}")
    public Response getUserRoleList(@PathVariable Integer appId) {

        String roles = userService.loadAccountRole(appId);
        Set<String> roleSet = JsonWebTokenUtil.split(roles);
        LOGGER.info(roleSet.toString());
        return new Response().Success(6666, "return roles success").addData("roles", roleSet);
    }


    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取用户列表", notes = "GET获取所有注册用户的信息列表")
    @GetMapping("/list/{start}/{limit}")
    public Response getUserList(@PathVariable Integer start, @PathVariable Integer limit) {

        PageHelper.startPage(start, limit);
        List<AuthUser> authUsers = userService.getUserList();
        authUsers.forEach(user -> user.setPassword(null));
        PageInfo pageInfo = new PageInfo(authUsers);
        return new Response().Success(6666, "return user list success").addData("pageInfo", pageInfo);
    }

    @ApiOperation(value = "给用户授权添加角色", httpMethod = "POST")
    @PostMapping("/authority/role")
    public Response authorityUserRole(HttpServletRequest request) {
//        Map<String, String> map = getRequestBody(request);
//        Integer uid = Integer.parseInt(map.get("uid"));
//        int roleId = Integer.parseInt(map.get("roleId"));
//        boolean flag = userService.authorityUserRole(uid, roleId);
//        return flag ? new Response().Success(6666, "authority success") : new Response().Fail(1111, "authority error");
        return  new Response();
    }

    @ApiOperation(value = "删除已经授权的用户角色", httpMethod = "DELETE")
    @DeleteMapping("/authority/role/{uid}/{roleId}")
    public Response deleteAuthorityUserRole(@PathVariable Integer uid, @PathVariable Integer roleId) {
        return userService.deleteAuthorityUserRole(uid, roleId) ? new Response().Success(6666, "delete success") : new Response().Fail(1111, "delete fail");
    }


    @ApiOperation(value = "用户登出", httpMethod = "POST")
    @PostMapping("/exit")
    public Response accountExit(HttpServletRequest request) {
    	request.getSession().removeAttribute("user");
//        Map<String, String> map = getRequestHeader(request);
//        String appId = map.get("appId");
//        if (StringUtils.isEmpty(appId)) {
//            return new Response().Fail(1111, "用户未登录无法登出");
//        }
//        String jwt = redisTemplate.opsForValue().get("JWT-SESSION-" + appId);
//        if (StringUtils.isEmpty(jwt)) {
//            return new Response().Fail(1111, "用户未登录无法登出");
//        }
//        redisTemplate.opsForValue().getOperations().delete("JWT-SESSION-" + appId);
//        LogExeManager.getInstance().executeLogTask(LogTaskFactory.exitLog(Integer.parseInt(appId), request.getRemoteAddr(), (short) 1, ""));

        return new Response().Success(6666, "用户退出成功");
    }
    @ApiOperation(value = "根据用户名称查询", httpMethod = "POST")
    @PostMapping("/queryByName")
    public Response queryInfo(HttpServletRequest request,String username) {
    	AuthUser authUser = userService.getUserByUsername(username);
        return new Response().Success(6666, "查询成功").addData("authUser", authUser);
    }
}
