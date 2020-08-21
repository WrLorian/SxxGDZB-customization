package com.kiwihouse.shiro.filter;


import com.alibaba.fastjson.JSON;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.RedisUtil;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.shiro.token.PasswordToken;
import com.kiwihouse.shiro.utils.ReqUtils;
import com.kiwihouse.shiro.utils.RespUtils;
import com.kiwihouse.util.AesUtil;
import com.kiwihouse.util.IpUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于 用户名密码 的认证过滤器
 *
 * @author tomsun28
 * @date 20:18 2018/2/10
 */
public class PasswordFilter extends AccessControlFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordFilter.class);

    private String _loginUrl = "/account/login";


    private String _register = "/account/register";

    private RedisUtil redisUtil;

    private boolean isEncryptPassword;


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = getSubject(request, response);
        // 如果其已经登录，再此发送登录请求
        //  拒绝，统一交给 onAccessDenied 处理
        return null != subject && subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {

        // 判断若为获取登录注册加密动态秘钥请求
//        if (isPasswordTokenGet(request)) {
//            //动态生成秘钥，redis存储秘钥供之后秘钥验证使用，设置有效期5秒用完即丢弃
//            String tokenKey = CommonUtil.getRandomString(16);
//            String userKey = CommonUtil.getRandomString(6);
//            try {
//                redisUtil.set("TOKEN_KEY_" + IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase() + userKey.toUpperCase(), tokenKey, 5);
//                // 动态秘钥response返回给前端
//                Message message = new Message();
//                message.Success(1000, "issued tokenKey success")
//                        .addData("tokenKey", tokenKey).addData("userKey", userKey.toUpperCase());
//                 RespUtils.responseWrite(JSON.toJSONString(message), response);
//
//            } catch (Exception e) {
//                LOGGER.warn("签发动态秘钥失败" + e.getMessage(), e);
//                Message message = new Message();
//                message.Success(1000, "issued tokenKey fail");
//                 RespUtils.responseWrite(JSON.toJSONString(message), response);
//            }
//            return false;
//        }
        Map<String, String> body = getRequestBodyMap(request);
        // 判断是否是登录请求
        if (isPasswordLoginPost(request, body)) {

            AuthenticationToken authenticationToken;
            try {
                authenticationToken = createPasswordToken(request, body);
            } catch (Exception e) {
                // response 告知无效请求
                Response responseMsg = new Response().Fail(Code.REQUEST_ERROR);
                RespUtils.responseWrite(JSON.toJSONString(responseMsg), response);
                return false;
            }

            Subject subject = getSubject(request, response);
            try {
                subject.login(authenticationToken);
                //登录认证成功,进入请求派发json web token url资源内
                return true;
            } catch (AuthenticationException e) {
                LOGGER.warn(authenticationToken.getPrincipal() + "::" + e.getMessage());
                // 返回response告诉客户端认证失败
                Response responseMsg = new Response().Fail(Code.LOGIN_FAIL,"用户名或密码错误！");
                RespUtils.responseWrite(JSON.toJSONString(responseMsg), response);
                return false;
            } catch (Exception e) {
                LOGGER.error(authenticationToken.getPrincipal() + "::认证异常::" + e.getMessage(), e);
                // 返回response告诉客户端认证失败
                Response responseMsg = new Response().Fail(Code.LOGIN_FAIL, "login fail");
                RespUtils.responseWrite(JSON.toJSONString(responseMsg), response);
                return false;
            }
        } else if (isAccountRegisterPost(request, body)) {
            // 判断是否为注册请求,若是通过过滤链进入controller注册
            return true;
        }
        // 之后添加对账户的找回等
        // response 告知无效请求
        Response responseMsg = new Response().Fail(Code.REQUEST_ERROR);
        RespUtils.responseWrite(JSON.toJSONString(responseMsg), response);
        return false;
    }

    private Map<String, String> getRequestBodyMap(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            return ReqUtils.getRequestBodyMap(request);
        }
        return new HashMap<>();
    }

    private boolean isPasswordTokenGet(ServletRequest request) {

        String tokenKey = ReqUtils.getParameter(request, "tokenKey");

        return (request instanceof HttpServletRequest)
                && "GET".equals(((HttpServletRequest) request).getMethod().toUpperCase())
                && "get".equals(tokenKey);
    }

    private boolean isPasswordLoginPost(ServletRequest request, Map<String, String> body) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest sRequest = (HttpServletRequest) request;
            String password = body.get("password");
            String methodName = sRequest.getRequestURI();
            String appId = body.get("username");
            return (request instanceof HttpServletRequest)
                    && "POST".equals((sRequest.getMethod().toUpperCase()))
                    && null != password
                    && null != appId
                    && _loginUrl.equals(methodName);
        }
        return false;

    }

    private boolean isAccountRegisterPost(ServletRequest request, Map<String, String> body) {
        if (request instanceof HttpServletRequest) {
            HttpServletRequest sRequest = (HttpServletRequest) request;
            String password = body.get("password");
            String methodName = sRequest.getRequestURI();
            String appId = body.get("username");
            return (request instanceof HttpServletRequest)
                    && "POST".equals((sRequest.getMethod().toUpperCase()))
                    && null != password
                    && null != appId
                    && _register.equals(methodName);
        }
        return false;
    }

    private AuthenticationToken createPasswordToken(ServletRequest request, Map<String, String> body) throws Exception {

        String username = body.get("username");
        String timestamp = body.get("timestamp");
        String password = body.get("password");
        String host = IpUtil.getIpFromRequest(WebUtils.toHttp(request));
        String userKey = body.get("userKey");
        if (isEncryptPassword) {
            String tokenKey = redisUtil.get("TOKEN_KEY_" + host.toUpperCase() + userKey);
            password = AesUtil.aesDecode(password, tokenKey);
        }
        return new PasswordToken(username, password, timestamp, host);
    }


    public void setEncryptPassword(boolean encryptPassword) {
        isEncryptPassword = encryptPassword;
    }

    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }
}
