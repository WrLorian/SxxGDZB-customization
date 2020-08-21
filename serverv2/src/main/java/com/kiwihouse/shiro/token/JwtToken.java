package com.kiwihouse.shiro.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * JWT token
 *
 * @author tomsun28
 * @date 19:37 2018/2/10
 */
@Data
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -5425617567065440209L;

    /**
     * 用户的IP
     */
    private String ipHost;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * json web token值
     */
    private String jwt;

    public JwtToken(String ipHost, String deviceInfo, String jwt) {
        this.ipHost = ipHost;
        this.deviceInfo = deviceInfo;
        this.jwt = jwt;
    }

    @Override
    public Object getPrincipal() {
        return this.ipHost;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }


}
