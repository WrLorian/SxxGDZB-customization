package com.kiwihouse.service;


import com.kiwihouse.dao.entity.AuthRole;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:10 2018/3/20
 */
public interface RoleService {

    /**
     * description TODO
     *
     * @param roleId 1
     * @param resourceId 2
     * @return boolean
     */
    boolean authorityRoleResource(int roleId, int resourceId);

    /**
     * description TODO
     *
     * @param role 1
     * @return boolean
     */
    boolean addRole(AuthRole role);

    /**
     * description TODO
     *
     * @param role 1
     * @return boolean
     */
    boolean updateRole(AuthRole role);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return boolean
     */
    boolean deleteRoleByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param roleId 1
     * @param resourceId 2
     * @return boolean
     */
    boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId);

    /**
     * description TODO
     *
     * @return java.util.List<com.kiwihouse.dao.entity.AuthRole>
     */
    List<AuthRole> getRoleList();
}
