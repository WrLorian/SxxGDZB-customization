package com.kiwihouse.util;


import com.alibaba.fastjson.JSON;
import com.kiwihouse.support.XssSqlHttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * http request response 过滤XSS SQL 数据工具类
 *
 * @author tomsun28
 * @date 10:13 2018/2/14
 */
public class RequestResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseUtil.class);
    private static final String STR_BODY = "body";

    /**
     * description 取request中的已经被防止XSS，SQL注入过滤过的key value数据封装到map 返回
     *
     * @param request 1
     * @return java.util.Map<java.lang.String, java.lang.String>
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public static Map<String, String> getParameterMap(HttpServletRequest request) {  
        // 参数Map  
        Map properties = request.getParameterMap();  
        // 返回值Map  
        Map<String, String> returnMap = new HashMap<String, String>();  
        Iterator entries = properties.entrySet().iterator();  
        Map.Entry entry;  
        String name = "";  
        String value = "";  
        while (entries.hasNext()) {  
            entry = (Map.Entry) entries.next();  
            name = (String) entry.getKey();  
            Object valueObj = entry.getValue();  
            if(null == valueObj){  
                value = "";  
            }else if(valueObj instanceof String[]){  
                String[] values = (String[])valueObj;  
                for(int i=0;i<values.length;i++){  
                    value = values[i] + ",";  
                }  
                value = value.substring(0, value.length()-1);  
            }else{  
                value = valueObj.toString();  
            }  
            returnMap.put(name, value);  
        }  
        return returnMap;  
    } 







}
