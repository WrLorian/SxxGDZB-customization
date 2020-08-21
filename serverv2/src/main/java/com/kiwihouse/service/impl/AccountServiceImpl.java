package com.kiwihouse.service.impl;


import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.domain.vo.Account;
import com.kiwihouse.service.AccountService;
import com.kiwihouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * @author tomsun28
 * @date 22:04 2018/3/7
 */
@Service("AccountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AuthUserMapper userMapper;

    @Autowired
    private UserService userService;

    @Override
    public Account loadAccount(Integer uid) throws DataAccessException {
        AuthUser user = userMapper.selectByPrimaryKey(uid);
        return user != null ? new Account(user.getUsername(), user.getPassword(), user.getSalt()) : null;
    }

    @Override
    public Account loadAccountByUsername(String username) throws DataAccessException {
        AuthUser user = userMapper.selectByUsername(username);
        return user != null ? new Account(user.getUsername(), user.getPassword(), user.getSalt()) : null;
    }

    @Override
    public boolean isAccountExistByUid(Integer uid) {
        AuthUser user = userMapper.selectByPrimaryKey(uid);
        return user != null ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean registerAccount(AuthUser account) throws DataAccessException {

        // 给新用户授权访客角色
        userService.authorityUserRole(account.getUid(), 103);

        return userMapper.insertSelective(account) == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public String loadAccountRole(Integer uid) throws DataAccessException {

        return userMapper.selectUserRoles(uid);
    }

    @Override
    public String loadAccountRoleByUsername(String username) throws DataAccessException {
        return userMapper.selectUserRolesByUsername(username);
    }
}
