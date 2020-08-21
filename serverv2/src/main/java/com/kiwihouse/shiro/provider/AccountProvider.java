package com.kiwihouse.shiro.provider;


import com.kiwihouse.domain.vo.Account;

/**
 *    数据库用户密码账户提供
 * @author tomsun28
 * @date 16:35 2018/2/11
 */
public interface AccountProvider {

    /**
     * description 数据库用户密码账户提供
     *
     * @param appId 1
     * @return com.kiwihouse.domain.vo.Account
     */
    Account loadAccount(Integer uid);

    Account loadAccountByUsername(String username);

}
