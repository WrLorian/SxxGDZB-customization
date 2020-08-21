package com.kiwihouse.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author tomsun28
 * @date 12:34 2018/2/27
 */
@Data
public class PasswordToken implements AuthenticationToken {

    private static final long serialVersionUID = 5929188597619912628L;

    private String username;
    private String password;
    private String timestamp;
    private String host;

    public PasswordToken(String username, String password, String timestamp, String host) {
        this.username = username;
        this.timestamp = timestamp;
        this.host = host;
        this.password = password;

    }


    @Override
    public Object getPrincipal() {
        return this.username;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }


}
