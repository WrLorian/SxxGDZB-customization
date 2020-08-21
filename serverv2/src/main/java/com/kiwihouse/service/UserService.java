package com.kiwihouse.service;


import com.kiwihouse.dao.entity.AuthUser;

import java.util.List;

/**
 * @author tomsun28
 * @date 21:14 2018/3/17
 */
public interface UserService {

    /**
     * description TODO
     *
     * @param appId 1
     * @return java.lang.String
     */
    String loadAccountRole(Integer appId);

    /**
     * description TODO
     *
     * @return java.util.List<com.kiwihouse.dao.entity.AuthUser>
     */
    List<AuthUser> getUserList();

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthUser>
     */
    List<AuthUser> getUserListByRoleId(Integer roleId);

    /**
     * description TODO
     *
     * @param appId  1
     * @param roleId 2
     * @return boolean
     */
    boolean authorityUserRole(Integer uid, int roleId);

    /**
     * description TODO
     *
     * @param uid    1
     * @param roleId 2
     * @return boolean
     */
    boolean deleteAuthorityUserRole(Integer uid, int roleId);

    /**
     * description TODO
     *
     * @param uid 1
     * @return com.kiwihouse.dao.entity.AuthUser
     */
    AuthUser getUserByUid(Integer uid);

    /**
     * description TODO
     *
     * @param username 1
     * @return com.kiwihouse.dao.entity.AuthUser
     */
    AuthUser getUserByUsername(String username);

    /**
     * description TODO
     *
     * @param roleId 1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthUser>
     */
    List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId);
}
