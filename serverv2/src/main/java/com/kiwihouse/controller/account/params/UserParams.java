package com.kiwihouse.controller.account.params;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.security.Timestamp;

/**
 * @author xin
 * @date 2020/7/16
 */
@Data
public class UserParams {
    @ApiParam(name = "用户名", required = true)
    private String username;
    @ApiParam(name = "密码", required = true)
    private String password;
    @ApiParam(value = "时间戳", required = true)
    private long timestamp;
}
