package com.kiwihouse.service;

import java.util.List;
import java.util.Map;

import com.kiwihouse.dao.entity.SysMenu;

public interface SysMenuService {
	/**
	 * 根据id获取用户所属权限菜单
	 * @param uid
	 * @return
	 */
	List<SysMenu> getAuthMenuList(Integer uid);
	/**
	 * 	修改菜单
	 * @param sysMenu
	 * @return
	 */
	boolean updateMenu(SysMenu sysMenu);
	/**
	 * 删除菜单
	 * @param ids
	 * @return
	 */
	boolean updateBatchMenuByIds(String[] ids);
	/**
	 * 添加菜单
	 * @param sysMenu 
	 * @return
	 */
	boolean insert(SysMenu sysMenu);
	

}
