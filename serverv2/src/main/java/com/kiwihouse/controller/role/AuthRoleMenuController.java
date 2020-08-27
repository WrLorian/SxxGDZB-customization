package com.kiwihouse.controller.role;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.controller.common.BaseController;
import com.kiwihouse.dao.entity.AuthRoleMenu;
import com.kiwihouse.domain.vo.AuthRoleMenuDetails;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.service.AuthRoleMenuService;

import io.swagger.annotations.ApiOperation;

@RequestMapping("/rolemenu")
@RestController
public class AuthRoleMenuController extends BaseController{
	@Autowired
    private AuthRoleMenuService authRoleMenuService;
	
	@ApiOperation(value = "获取用户对应角色的菜单信息以及用户没有的菜单信息", notes = "菜单权限操作", httpMethod = "GET")
    @GetMapping("menu")
    public Map<String, Object> list(Integer roleId,
    		  Integer page,
    		  Integer limit,AuthRoleMenuDetails authRoleMenuDetails,Integer trigger) {
    	try {
    		if(authRoleMenuDetails==null) {
    			authRoleMenuDetails = new AuthRoleMenuDetails();
    		}
    		map = authRoleMenuService.selectPage(page,limit,roleId,authRoleMenuDetails,trigger);
    		map.put("code", 0);
    		map.put("msg",Code.QUERY_SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			return putMsgToJsonString(0, Code.QUERY_FAIL.getMsg(), 0, null);
		}
        return map;
    }
	
	@ApiOperation(value = "添加角色菜单", httpMethod = "POST")
    @PostMapping("")
    public Response addRoleMenu(@RequestBody AuthRoleMenu authRoleMenu ) {
        boolean flag = authRoleMenuService.insertBatch(authRoleMenu.getRoleId(),authRoleMenu.getIds());
        if (flag) {
            return new Response().Success(6666, "add role success");
        } else {
            return new Response().Fail(111, "add role fail");
        }
    }
	
	@ApiOperation(value = "修改角色菜单权限", httpMethod = "PUT")
    @PutMapping("")
    public Response updRoleMenu(@RequestBody AuthRoleMenu authRoleMenu ) {
        boolean flag = authRoleMenuService.update(authRoleMenu);
        if (flag) {
            return new Response().Success(6666, "add role success");
        } else {
            return new Response().Fail(111, "add role fail");
        }
    }
}
