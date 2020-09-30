package com.kiwihouse.controller.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.util.StringUtils;

/**
 *   controller基础抽象类
 * @author tomsun28
 * @date 11:09 2018/3/20
 */
public abstract class BaseController{
    @Value("${kiwihouse.download.url}")
    public String downloadUrl;
    /**
     * description 获得来的request中的 key value数据封装到map返回
     *
     * @param request 1
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
//    @SuppressWarnings("rawtypes")
//    protected Map<String, String> getRequestParameter(HttpServletRequest request) {
//
//        return RequestResponseUtil.getRequestParameters(request);
//    }
//
//    protected Map<String,String> getRequestBody(HttpServletRequest request) {
//        return RequestResponseUtil.getRequestBodyMap(request);
//    }
//
//    protected Map<String, String > getRequestHeader(HttpServletRequest request) {
//        return RequestResponseUtil.getRequestHeaders(request);
//    }
//	@RequestMapping("/page/{url}/{path}")
//	public String returnPage(@PathVariable String url,@PathVariable String path) {
//		return url+"/"+ path;
//	}
	@Autowired
	AuthUserMapper authUserMapper;
	/**
     * 页面跳转
     */
    public String redirect(String url)
    {
        return StringUtils.format("redirect:{}", url);
    }
    /**
     * 获取登录用户名称
     * @return
     */
    public String getUserName(HttpServletRequest request,HttpServletResponse response)
    {
    	System.out.println("username------------->" + SecurityUtils.getSubject().getPrincipal());
    	try {
    		if( SecurityUtils.getSubject().getPrincipals() == null) {
    			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
    			requestDispatcher.forward(request, response);
    		}
    		JSONObject jo = JSON.parseObject(SecurityUtils.getSubject().getPrincipals().toString().substring(4, SecurityUtils.getSubject().getPrincipals().toString().length()));
            System.out.println(jo.toJSONString());
        	return jo.getString("sub");
    	}catch (Exception e) {
			// TODO: handle exception
		}
    	return SecurityUtils.getSubject().getPrincipals().toString();
    }
    
    public AuthUser getUser() {
    	AuthUser authUser  = authUserMapper.selectByUsername("admin");
		return authUser;
    }
    
    protected Map<String, Object> map = new HashMap<String, Object>();

	/**
	 * @param code  1 成功 0 失败
	 * @param msg   消息内容
	 * @param count 最大条数
	 * @param data  具体内容
	 * @return
	 */
	public Map<String, Object> putMsgToJsonString(int code, String msg, int count, Object data) {
		map.put("code", code);
		map.put("msg", msg);
		map.put("count", count);
		map.put("data", data);
		return map;
	}
	
	
	public String setFileDownloadHeader(HttpServletRequest request, String fileName) throws UnsupportedEncodingException
    {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        if (agent.contains("MSIE"))
        {
            // IE浏览器
            filename = URLEncoder.encode(filename, "utf-8");
            filename = filename.replace("+", " ");
        }
        else if (agent.contains("Firefox"))
        {
            // 火狐浏览器
            filename = new String(fileName.getBytes(), "ISO8859-1");
        }
        else if (agent.contains("Chrome"))
        {
            // google浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        else
        {
            // 其它浏览器
            filename = URLEncoder.encode(filename, "utf-8");
        }
        return filename;
    }
}
