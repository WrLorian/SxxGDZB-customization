package com.kiwihouse.shiro.utils;

import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

/**
 * @author xin
 * @date 2020/7/17
 */
public class RespUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(RespUtils.class);

    /**
     * description 封装response  统一json返回
     *
     * @param outStr   1
     * @param response 2
     */
    public static void responseWrite(String outStr, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
