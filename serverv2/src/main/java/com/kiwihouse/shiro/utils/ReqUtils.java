package com.kiwihouse.shiro.utils;

import com.alibaba.fastjson.JSON;
import com.kiwihouse.support.XssSqlHttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xin
 * @date 2020/7/17
 */
public class ReqUtils {
    /**
     * description 读取request 已经被防止XSS，SQL注入过滤过的 请求参数key 对应的value
     *
     * @param request 1
     * @param key     2
     * @return java.lang.String
     */
    public static String getParameter(ServletRequest request, String key) {
        return ReqUtils.getRequest(request).getParameter(key);
    }

    /**
     * description 读取request 已经被防止XSS，SQL注入过滤过的 请求头key 对应的value
     *
     * @param request 1
     * @param key     2
     * @return java.lang.String
     */
    public static String getHeader(ServletRequest request, String key) {
        return ReqUtils.getRequest(request).getHeader(key);
    }


    public static HttpServletRequest getRequest(ServletRequest request) {
        return new XssSqlHttpServletRequestWrapper((HttpServletRequest) request);
    }

    public static Map<String, String> getRequestBodyMap(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>(16);
        try {
            String inputStr = IOUtils.toString(request.getInputStream());
            if (!inputStr.isEmpty()) {
                Map<String, Object> maps = JSON.parseObject(inputStr, Map.class);
                maps.forEach((key, value) -> dataMap.put(key, String.valueOf(value)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMap;
    }
}
