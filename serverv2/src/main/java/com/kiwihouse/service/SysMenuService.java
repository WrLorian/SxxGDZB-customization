package com.kiwihouse.service;

import java.util.List;

import com.kiwihouse.dao.entity.SysMenu;

public interface SysMenuService {
	/**
	 * 根据id获取用户所属权限菜单
	 * @param uid
	 * @return
	 */
	List<SysMenu> getAuthMenuList(Integer uid);

}
