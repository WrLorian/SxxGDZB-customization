package com.kiwihouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.dao.entity.AuthRoleMenu;
import com.kiwihouse.dao.mapper.AuthRoleMenuMapper;
import com.kiwihouse.domain.vo.AuthRoleMenuDetails;
import com.kiwihouse.service.AuthRoleMenuService;
@Service
public class AuthRoleMenuServiceImpl implements AuthRoleMenuService{
	@Autowired
	AuthRoleMenuMapper authRoleMenuMapper;

	@Override
	public Map<String, Object> selectPage(Integer currentPage, Integer pageSize, Integer roleId,AuthRoleMenuDetails authRoleMenuDetails,Integer trigger) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("count", authRoleMenuMapper.selectRoleMenuCount(roleId,authRoleMenuDetails, trigger));
		List<AuthRoleMenuDetails> list = new ArrayList<AuthRoleMenuDetails>();
		if (currentPage != null) {
			list = authRoleMenuMapper.selectRoleMenuList((currentPage - 1) * pageSize,pageSize,roleId,authRoleMenuDetails,trigger);
		} else {
			list = authRoleMenuMapper.selectRoleMenuList(null,null,roleId,authRoleMenuDetails,trigger);
		}
		map.put("data", list);
		return map;
	}

	@Override
	public boolean insertBatch(Integer roleId, String ids) {
		String [] arr = ids.split(",");
		// TODO Auto-generated method stub
		List<AuthRoleMenu> list = new ArrayList<AuthRoleMenu>();
		for(String menuId : arr) {
			AuthRoleMenu authRoleMenu = new AuthRoleMenu();
			authRoleMenu.setRoleId(roleId);
			authRoleMenu.setMenuId(Integer.valueOf(menuId));
			list.add(authRoleMenu);
		}
		int num = authRoleMenuMapper.insertBatch(list);
		 return num > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean update(AuthRoleMenu authRoleMenu) {
		int num = authRoleMenuMapper.update(authRoleMenu);
		// TODO Auto-generated method stub
		return num == 1 ? Boolean.TRUE : Boolean.FALSE;
	}
}
