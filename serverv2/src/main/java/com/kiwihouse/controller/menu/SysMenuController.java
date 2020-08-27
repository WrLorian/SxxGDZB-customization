package com.kiwihouse.controller.menu;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.dao.entity.AuthRole;
import com.kiwihouse.dao.entity.SysMenu;
import com.kiwihouse.dao.mapper.SysDictionaryMapper;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.SysMenuService;
import com.kiwihouse.util.TreeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.val;
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

	@Autowired
	SysMenuService sysMenuService;
	@Autowired
	SysDictionaryMapper sysDictionaryMapper;
	
	@ApiOperation(value = "根据UID获取用户所属菜单显示在左侧导航栏", httpMethod = "GET",notes = "根据UID")
	@PostMapping("/authMenuList")
	@ResponseBody
	public Response getAuthMenuList(Integer uid) {
        List<SysMenu> treeNodes = new ArrayList<>();
        //得到左菜单
        treeNodes = sysMenuService.getAuthMenuList(uid);
        JSONObject jo = new JSONObject();
        JSONObject jo_1 = new JSONObject();
        //删除首页
        for(int i=0; i< treeNodes.size();i++) {
        	if(treeNodes.get(i).getOrderNum()<0) {
        		jo_1.put("title",treeNodes.get(i).getName());
        	    jo_1.put("url",treeNodes.get(i).getUrl());
        		treeNodes.remove(i);
        	}
        }
        //形成树结构
        List<SysMenu> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,0);
        jo.put("homeInfo", jo_1);
        jo.put("menuInfo", menuTreeNodes);
        
        String vaule = sysDictionaryMapper.selectByKey("LOGO");
        JSONObject str = JSON.parseObject(vaule);
        System.out.println(str);
        jo.put("logoInfo", str);
		return new Response().Success(6666,"return menu list success").addData("menuTree",jo);
	}
	
	@ApiOperation(value = "根据UID获取用户所属菜单", httpMethod = "GET",notes = "根据UID")
	@PostMapping("/authMenuLists")
	@ResponseBody
	public Response getAuthMenuLists(Integer uid) {
		Map<String, Object> map = new HashMap<String, Object>();
        List<SysMenu> treeNodes = new ArrayList<>();
        
        //得到左菜单
        treeNodes = sysMenuService.getAuthMenuList(uid);
        //treeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,0);
        map.put("count",treeNodes.size());
        map.put("code",0);
        map.put("masg", "success");
        map.put("data", treeNodes);
		return new Response().Success(6666,"return menu list success").addData("menuTree",map);
	}
	
	@ApiOperation(value = "更新菜单", httpMethod = "PUT")
    @PutMapping("")
    public Response updateMenu(@RequestBody SysMenu sysMenu) {
		System.out.println("ParentId------------>" + sysMenu.getParentId());
		boolean flag = sysMenuService.updateMenu(sysMenu);
        if (flag) {
            return new Response().Success(6666, "update success");
        } else {
            return new Response().Fail(1111, "update fail");
        }
    }
	@ApiOperation(value = "删除菜单", httpMethod = "DELETE")
	@DeleteMapping("{ids}")
    public Response deleteMenu(@PathVariable String ids) {
		String [] idsStrArr = ids.split("_");
		for(String str : idsStrArr) {
			System.out.println(str.toString());
		}
		
		boolean flag = sysMenuService.updateBatchMenuByIds(idsStrArr);
        if (flag) {
            return new Response().Success(6666, "delete success");
        } else {
            return new Response().Fail(1111, "delete fail");
        }
    }
	
	@ApiOperation(value = "添加菜单", httpMethod = "POST")
    @PostMapping("")
    public Response addRole(@RequestBody SysMenu sysMenu) {

        boolean flag = sysMenuService.insert(sysMenu);
        if (flag) {
            return new Response().Success(6666, "add menu success");
        } else {
            return new Response().Fail(111, "add menu fail");
        }
    }
}
