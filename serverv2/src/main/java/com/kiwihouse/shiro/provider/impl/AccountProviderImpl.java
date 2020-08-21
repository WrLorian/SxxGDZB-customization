package com.kiwihouse.shiro.provider.impl;


import com.kiwihouse.domain.vo.Account;
import com.kiwihouse.service.AccountService;
import com.kiwihouse.shiro.provider.AccountProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author tomsun28
 * @date 9:22 2018/2/13
 */
@Service("AccountProvider")
public class AccountProviderImpl implements AccountProvider {

    @Autowired
    @Qualifier("AccountService")
    private AccountService accountService;

    @Override
    public Account loadAccount(Integer uid) {
        return accountService.loadAccount(uid);
    }

    @Override
    public Account loadAccountByUsername(String username) {
        return accountService.loadAccountByUsername(username);
    }
}
