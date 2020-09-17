package com.kiwihouse.service.impl;

import java.util.ArrayList;
import java.util.List;

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
	public List<SysMenu> getAuthMenuList(Integer roleId,Integer visible) {
		
		return sysMenuMapper.getAuthMenuList(roleId, visible);
	}

	@Override
	public boolean updateMenu(SysMenu sysMenu) {
		// TODO Auto-generated method stub
		 int num = sysMenuMapper.updateMenu(sysMenu);
	     return num == 1 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean updateBatchMenuByIds(String[] ids,Integer visible) {
		int num = sysMenuMapper.updateBatchMenuByIds(ids,visible);
		//删除绑定数据
		if(visible == 0) {
			//authRoleMenuService.deleteBatchByMenuId(ids);
		}
        return num > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean insert(SysMenu sysMenu) {
		int num = sysMenuMapper.insert(sysMenu);
		//将新增的菜单加至当前角色
		authRoleMenuService.insertBatch(sysMenu.getRoleId(), sysMenu.getId().toString());
        return num > 0 ? Boolean.TRUE : Boolean.FALSE;
	}

	@Override
	public boolean deleteBatchMenuByIds(String[] idsStrArr) {
		// TODO Auto-generated method stub
//		int num = authRoleMenuService.deleteBatch(idsStrArr);
		int num = sysMenuMapper.deleteBatch(idsStrArr);
        return num > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	 
	

}
