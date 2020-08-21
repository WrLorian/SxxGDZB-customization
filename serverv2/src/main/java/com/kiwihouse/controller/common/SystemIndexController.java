package com.kiwihouse.controller.common;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.mapper.AuthUserMapper;


//@Controller
//public class SystemIndexController extends BaseController{
//
//	@Autowired
//	AuthUserMapper authUserMapper;
//	
//	// 系统首页
//    @GetMapping("/index")
//    public ModelAndView index(ModelMap mmap,HttpServletRequest request)
//    {
//    	AuthUser authUser = authUserMapper.selectByUsername(getUserName());
//    	System.out.println("username:-------------->" +authUser.getUsername());
////    	AuthUser authUser = (AuthUser) request.getSession().getAttribute("user");
//    	mmap.addAttribute("user", authUser);
//        return new ModelAndView("index");
//    }
//}
