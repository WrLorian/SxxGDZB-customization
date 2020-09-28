package com.kiwihouse.controller.account.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author xin
 * @date 2020/7/16
 */
@Data
@ApiModel(description = "登录注册信息")
public class UserParams {
    @ApiModelProperty(value = "用户名",name = "username",required =true,position =1)
    private String username;
    @ApiModelProperty(value = "密码",name = "password",required =true,position =2)
    private String password;
    @ApiModelProperty(value = "时间戳",name = "timestamp",required =false,position =3)
    private long timestamp;
}
