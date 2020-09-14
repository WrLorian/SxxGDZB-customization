package com.kiwihouse.service.impl;

import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.dao.mapper.AuthUserRoleMapper;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.dao.entity.AuthUserRole;
import com.kiwihouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author tomsun28
 * @date 21:15 2018/3/17
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthUserMapper userMapper;

    @Autowired
    private AuthUserRoleMapper authUserRoleMapper;

    @Override
    public String loadAccountRole(Integer appId) throws DataAccessException {

        return userMapper.selectUserRoles(appId);
    }

    @Override
    public List<AuthUser> getUserList() throws DataAccessException {
        return userMapper.selectUserList();
    }

    @Override
    public List<AuthUser> getUserListByRoleId(Integer roleId) throws DataAccessException {
        return userMapper.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean authorityUserRole(Integer uid, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setRoleId(roleId);
        authUserRole.setUserId(uid);
        authUserRole.setCreateTime(new Date());
        authUserRole.setUpdateTime(new Date());
        return authUserRoleMapper.insert(authUserRole) == 1? Boolean.TRUE :Boolean.FALSE;
    }

    @Override
    public boolean deleteAuthorityUserRole(Integer uid, int roleId) throws DataAccessException {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(uid);
        authUserRole.setRoleId(roleId);
        return authUserRoleMapper.deleteByUniqueKey(authUserRole) == 1? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public AuthUser getUserByUid(Integer uid) throws DataAccessException {

        return userMapper.selectByPrimaryKey(uid);
    }
    @Override
    public AuthUser getUserByUsername(String username){
        return userMapper.selectByUsername(username);
    }
    @Override
    public List<AuthUser> getNotAuthorityUserListByRoleId(Integer roleId) throws DataAccessException {

        return userMapper.selectUserListExtendByRoleId(roleId);
    }

	@Override
	public List<Map<String, Integer>> queryAuthUserByRoleUserId(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		return userMapper.queryAuthUserByRoleUserId(userId,roleId);
	}
}
