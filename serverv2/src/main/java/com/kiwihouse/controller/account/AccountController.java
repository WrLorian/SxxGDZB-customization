package com.kiwihouse.controller.account;


import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.RedisUtil;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.controller.account.params.UserParams;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.mapper.AuthUserRoleMapper;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.AccountService;
import com.kiwihouse.service.UserService;
import com.kiwihouse.support.factory.LogTaskFactory;
import com.kiwihouse.support.manager.LogExeManager;
import com.kiwihouse.util.*;

import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.ApiOperation;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * post新增,get读取,put完整更新,patch部分更新,delete删除
 *
 * @author tomsun28
 * @date 14:40 2018/3/8
 */
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);


    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;

    @Value("${kiwihouse.enableEncryptPassword}")
    private boolean isEncryptPassword;

    /**
     * description 登录签发 JWT ,这里已经在 passwordFilter 进行了登录认证
     *
     * @param request  1
     * @param response 2
     * @return com.kiwihouse.domain.vo.Message
     */
    @ApiOperation(value = "用户登录", notes = "POST用户登录签发JWT")
    @PostMapping("/login")
    @ResponseBody
    public Response accountLogin(@RequestBody UserParams params, HttpServletRequest request, HttpServletResponse response) {

        AuthUser authUser = userService.getUserByUsername(params.getUsername());
        authUser.setPassword(null);
        authUser.setSalt(null);
        authUser.setRoleId(authUserRoleMapper.selectByUid(authUser.getUid()));
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accountService.loadAccountRoleByUsername(params.getUsername());
        //根据roles 查询 相应的 权限资源
        //-
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        Integer refreshPeriodTime = 36000;
        String jwt = JsonWebTokenUtil.issueJWT(UUID.randomUUID().toString(), params.getUsername(),
                "token-server", refreshPeriodTime >> 1, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        //redisUtil.set("JWT-SESSION-" + params.getUsername(), jwt, refreshPeriodTime);
        LogExeManager.getInstance().executeLogTask(LogTaskFactory.loginLog(1, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "登录成功"));
        //request.getSession().setAttribute("user", authUser);
        return new Response().Success(Code.LOGIN_SUCC, "issue jwt success").addData("jwt", jwt).addData("user", authUser);
    }

    /**
     * description 用户账号的注册
     *
     * @param request  1
     * @param response 2
     * @return com.kiwihouse.domain.vo.Message
     */
    @ApiOperation(value = "用户注册", notes = "POST用户注册")
    @PostMapping("/register")
    public Response accountRegister(HttpServletRequest request, HttpServletResponse response) {

//        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
//        AuthUser authUser = new AuthUser();
//        Integer uid = Integer.parseInt(params.get("uid"));
//        String password = params.get("password");
//        String userKey = params.get("userKey");
//        if (StringUtils.isEmpty(uid) || StringUtils.isEmpty(password)) {
//            // 必须信息缺一不可,返回注册账号信息缺失
//            return new ResponseMsg().Fail(1111, "账户信息缺失");
//        }
//        if (accountService.isAccountExistByUid(uid)) {
//            // 账户已存在
//            return new ResponseMsg().Fail(1111, "账户已存在");
//        }
//
//        authUser.setUid(uid);
//
//        if (isEncryptPassword) {
//            // 从Redis取出密码传输加密解密秘钥
//            String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey);
//            password = AesUtil.aesDecode(password, tokenKey);
//        }
//        String salt = CommonUtil.getRandomString(6);
//        // 存储到数据库的密码为 MD5(原密码+盐值)
//        authUser.setPassword(Md5Util.md5(password + salt));
//        authUser.setSalt(salt);
//        authUser.setCreateTime(new Date());
//        if (!StringUtils.isEmpty(params.get(STR_USERNAME))) {
//            authUser.setUsername(params.get(STR_USERNAME));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_REALNAME))) {
//            authUser.setRealName(params.get(STR_REALNAME));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_AVATAR))) {
//            authUser.setAvatar(params.get(STR_AVATAR));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_PHONE))) {
//            authUser.setPhone(params.get(STR_PHONE));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_EMAIL))) {
//            authUser.setEmail(params.get(STR_EMAIL));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_SEX))) {
//            authUser.setSex(Byte.valueOf(params.get(STR_SEX)));
//        }
//        if (!StringUtils.isEmpty(params.get(STR_WHERE))) {
//            authUser.setCreateWhere(Byte.valueOf(params.get(STR_WHERE)));
//        }
//        authUser.setStatus((byte) 1);
//
//        if (accountService.registerAccount(authUser)) {
//            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 1, "注册成功"));
//            return new ResponseMsg().Success(2002, "注册成功");
//        } else {
//            LogExeManager.getInstance().executeLogTask(LogTaskFactory.registerLog(uid, IpUtil.getIpFromRequest(WebUtils.toHttp(request)), (short) 0, "注册失败"));
//            return new ResponseMsg().Success(1111, "注册失败");
//        }
        return new Response();
    }
    
}
