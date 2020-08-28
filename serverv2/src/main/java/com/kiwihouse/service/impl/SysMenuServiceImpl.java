package com.kiwihouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.dao.entity.SysMenu;
import com.kiwihouse.dao.mapper.SysMenuMapper;
import com.kiwihouse.service.AuthRoleMenuService;
import com.kiwihouse.service.SysMenuService;
@Service
public class SysMenuServiceImpl implements SysMenuService{
	@Autowired
	SysMenuMapper sysMenuMapper;
	@Autowired
	AuthRoleMenuService authRoleMenuService;
	@Override
	public List<SysMenu> getAuthMenuList(Integer uid) {
		
		return sysMenuMapper.getAuthMenuList(uid);
	}

	@Override
	public boolean updateMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		 int num = sysMenuMapper.updateMenu(sysMenu);
	     return num == 1? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean updateBatchMenuByIds(String[] ids) {
		// TODO Auto-generated method stub
		int num = sysMenuMapper.updateBatchMenuByIds(ids);
        return num == 1? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean insert(SysMenu sysMenu) {
		int num = sysMenuMapper.insert(sysMenu);
		System.out.println("新添加的ID" + sysMenu.getId());
		//将新增的菜单加至当前角色
		authRoleMenuService.insertBatch(sysMenu.getRoleId(), sysMenu.getId().toString());
        return num == 1? Boolean.TRUE : Boolean.FALSE;
	}
	 
	

}
