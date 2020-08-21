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
     * 根据id获取用户所属权限菜单
     * @param uid
     * @return
     */
	List<SysMenu> getAuthMenuList(Integer uid);
}