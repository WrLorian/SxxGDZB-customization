package com.kiwihouse.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xin
 * @date 2020/7/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    Integer uid;//用户id
    Integer roleId;
    String displayName;
    String username;
    String password;
    String phone;
    String salt;
    boolean state;

    /**
     * 密码盐
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
