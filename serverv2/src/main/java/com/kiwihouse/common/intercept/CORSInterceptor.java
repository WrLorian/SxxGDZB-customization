package com.kiwihouse.common.intercept;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 跨域配置
 * @author yjzn
 * @date 2019年12月19日15:19:20
 */
@Component
public class CORSInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin", origin);

//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:9527");
//        response.setHeader("Access-Control-Allow-Origin", "http://106.53.1.5:8701");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,dz-usr,X-Token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Expose-Headers", "VerifyCode");

        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (request.getMethod().equals("OPTIONS")){
            response.setStatus(200);
            return false;
        }
        return true;
    }

}
