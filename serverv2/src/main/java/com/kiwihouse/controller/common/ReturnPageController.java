package com.kiwihouse.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.mapper.AuthUserMapper;

import io.swagger.annotations.Api;
@Api(tags = "页面跳转接口")
@RestController
@RequestMapping("/admin")
public class ReturnPageController extends BaseController{
	@Autowired
	AuthUserMapper authUserMapper;
	
	private static String loginPage = "login";
	
	@RequestMapping(value = "/index")
	public ModelAndView returnPage(ModelMap mmap,HttpServletRequest request) {
//		AuthUser authUser  = authUserMapper.selectByUsername("admin");
		AuthUser authUser = (AuthUser) request.getSession().getAttribute("user");
    	mmap.addAttribute("user", authUser);
    	System.out.println("获取对象：------------->" + SecurityUtils.getSubject().getPrincipal());
		return new ModelAndView("index");
	}
	@RequestMapping(value = "/unauth")
	public ModelAndView unauthPage(ModelMap mmap,HttpServletRequest request) {
		return new ModelAndView("unauth");
	}
	
	@RequestMapping(value = "/login")
	public ModelAndView Logout() {
		SecurityUtils.getSubject().logout();
		System.out.println("-----------退出----------------------");
		return new ModelAndView(loginPage);
	}
	
	@RequestMapping(value = "/{name}/{url}")
	public ModelAndView returnPageTwo(@PathVariable String name,@PathVariable String url) {
		return new ModelAndView(name + "/" + url);
		
	}
}
