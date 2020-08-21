package com.kiwihouse.config;

import com.kiwihouse.common.intercept.CORSInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author yjzn
 * @date 2019年12月19日15:36:58
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CORSInterceptor corsInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
            先注册拦截器，优先级高
         */
        registry.addInterceptor(corsInterceptor)
                .addPathPatterns("/**");

//        registry.addInterceptor(privilegeInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/user/register")
//                .excludePathPatterns("/admin/exit")
//                .excludePathPatterns("/excel")
////                .excludePathPatterns("/statistics/**")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"); //不拦截swagger-ui;
//
//        registry.addInterceptor(loginInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/admin/login")
//                .excludePathPatterns("/user/register")
//                .excludePathPatterns("/excel")
////                .excludePathPatterns("/statistics/**")
//                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"); //不拦截swagger-ui;
//

    }

}