//package com.kiwihouse.util;
//
//
//import com.alibaba.fastjson.JSON;
//import com.kiwihouse.support.XssSqlHttpServletRequestWrapper;
//import org.apache.commons.io.IOUtils;
//import org.apache.shiro.web.util.WebUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import java.io.*;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * http request response 过滤XSS SQL 数据工具类
// *
// * @author tomsun28
// * @date 10:13 2018/2/14
// */
//public class RequestResponseUtil {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseUtil.class);
//    private static final String STR_BODY = "body";
//
//    /**
//     * description 取request中的已经被防止XSS，SQL注入过滤过的key value数据封装到map 返回
//     *
//     * @param request 1
//     * @return java.util.Map<java.lang.String, java.lang.String>
//     */
//    public static Map<String, String> getRequestParameters(ServletRequest request) {
//        Map<String, String> dataMap = new HashMap<>(16);
//        Enumeration enums = request.getParameterNames();
//        while (enums.hasMoreElements()) {
//            String paraName = (String) enums.nextElement();
//            String paraValue = RequestResponseUtil.getRequest(request).getParameter(paraName);
//            if (null != paraValue && !"".equals(paraValue)) {
//                dataMap.put(paraName, paraValue);
//            }
//        }
//        return dataMap;
//    }
//
//    /**
//     * description 获取request中的body json 数据转化为map
//     *
//     * @param request 1
//     * @return java.util.Map<java.lang.String, java.lang.String>
//     */
//    @SuppressWarnings("unchecked")

//

//

//
//
//}
