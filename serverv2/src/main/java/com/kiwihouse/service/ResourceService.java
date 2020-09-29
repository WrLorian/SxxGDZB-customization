package com.kiwihouse.service;

import com.kiwihouse.dao.entity.AuthResource;
import com.kiwihouse.domain.vo.Response;

import java.util.List;
import java.util.Map;

/**
 * @author tomsun28
 * @date 13:39 2018/3/18
 */
public interface ResourceService {

    /**
     * description TODO
     *
     * @param appId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getAuthorityMenusByUid(String appId);

    /**
     * description TODO
     *
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getMenus();

    /**
     * description TODO
     *
     * @param menu 1
     * @return java.lang.Boolean
     */
    Boolean addMenu(AuthResource menu);

    /**
     * description TODO
     *
     * @param menu 1
     * @return java.lang.Boolean
     */
    Boolean modifyMenu(AuthResource menu);

    /**
     * description TODO
     *
     * @param menuId 1
     * @return java.lang.Boolean
     */
    Boolean deleteMenuByMenuId(Integer menuId);


    /**
     * description TODO
     *
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getApiTeamList();

    /**
     * description TODO
     *
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getApiList();

    /**
     * description TODO
     *
     * @param teamId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getApiListByTeamId(Integer teamId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getAuthorityApisByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getAuthorityMenusByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getNotAuthorityApisByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthResource>
     */
    List<AuthResource> getNotAuthorityMenusByRoleId(Integer roleId);
    /**
     * 查询权限列表
     * @param i
     * @param limit
     * @param auResourceVo 
     * @return
     */
	Map<String, Object> selectPage(Integer page, Integer limit,Integer roleId, AuthResource authResource);
	/**
	 * 批量删除菜单
	 * @param menuIds
	 * @return
	 */
	Boolean deleteBatch(String menuIds);
	/**
	 * 查询静态资源列表
	 * @return 
	 */
	Response selectStaticResource();
}
