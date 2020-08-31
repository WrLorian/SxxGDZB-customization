package com.kiwihouse.dao.mapper;

import java.util.List;

import com.kiwihouse.dao.entity.AuthRoleMenu;
import com.kiwihouse.domain.vo.AuthRoleMenuDetails;

import io.lettuce.core.dynamic.annotation.Param;


public interface AuthRoleMenuMapper {
    int insert(AuthRoleMenu record);

    int insertSelective(AuthRoleMenu record);
    
    /**
	 * 根据角色查询对应的菜单列表记录条数
	 * @param roleId
     * @param authRoleMenuDetails 
     * @param trigger 
	 * @return
	 */
	int selectRoleMenuCount(@Param("roleId") Integer roleId,
			@Param("authRoleMenuDetails") AuthRoleMenuDetails authRoleMenuDetails, 
			@Param("trigger") Integer trigger);
	/**
	 * 根据角色查询对应的菜单列表
	 * @param i
	 * @param pageSize
	 * @param roleId
	 * @return
	 */
	List<AuthRoleMenuDetails> selectRoleMenuList(@Param("currentPage") Integer currentPage, 
			@Param("pageSize") Integer pageSize, 
			@Param("roleId") Integer roleId,
			@Param("authRoleMenuDetails") AuthRoleMenuDetails authRoleMenuDetails,
			@Param("trigger") Integer trigger);
	/**
	 * 批量添加角色菜单关联数据
	 * @param list 
	 * @param roleId
	 * @param ids
	 * @return
	 */
	int insertBatch(List<AuthRoleMenu> list);
	/**
	 * 修改角色与菜单关联
	 * @param authRoleMenu
	 * @return
	 */
	int update(AuthRoleMenu authRoleMenu);
	/**
	 * 批量删除
	 * @param list
	 * @return
	 */
	int deleteBatch(List<AuthRoleMenu> list);
	/**
	 * 根据roleID删除 角色与菜单关联数据
	 * @param roleId
	 * @return
	 */
	int deleteByRole(Integer roleId);
}