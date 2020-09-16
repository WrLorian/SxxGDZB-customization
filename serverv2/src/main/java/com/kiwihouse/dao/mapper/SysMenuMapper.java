package com.kiwihouse.dao.mapper;

import java.util.List;

import com.kiwihouse.dao.entity.SysMenu;


public interface SysMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int insert(SysMenu record);

    int insertSelective(SysMenu record);

    SysMenu selectByPrimaryKey(Integer menuId);

    int updateByPrimaryKeySelective(SysMenu record);

    int updateByPrimaryKey(SysMenu record);
    /**
     * 	根据id获取用户所属权限菜单
     * @param uid
     * @return
     */
	List<SysMenu> getAuthMenuList(Integer roleId);
	/**
	 * 	修改菜单
	 * @param sysMenu
	 * @return
	 */
	int updateMenu(SysMenu sysMenu);
	/**
	 * 根据ids修改删除菜单
	 * @param ids
	 * @return
	 */
	int updateBatchMenuByIds(String [] ids);
	
}