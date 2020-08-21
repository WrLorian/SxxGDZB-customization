package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2019-12-19-下午 8:02
 */
@ToString
@Getter
@Setter
@ApiModel(description = "用户信息录入表单")
public class UserAddVo {


    //用户信息
    @ApiModelProperty(hidden = true)
    private String userId;      //添加用户返回主键值，用户添加对应的联系人
    @ApiModelProperty(hidden = true)
    private String addTime;

    @NotBlank(message = "userName is not null")
    @ApiModelProperty(value = "用户名",name = "userName",required = true,example = "张三")
    private String userName;

    @NotBlank(message = "password is not null")
    @ApiModelProperty(value = "密码",name = "password",required = true,example = "sha256(123456)")
    private String password;

    @NotBlank(message = "phone is not null")
    @ApiModelProperty(value = "电话号码",name = "phone",required = true,example = "13027301010")
    private String phone;

    @ApiModelProperty(value = "用户头像",name = "imgUrl",required = false,example = "http://localhost:8090/pciture.jpg")
    private String imgUrl;

    //联系人信息
    @ApiModelProperty(value = "联系人1",name = "ctsName1",required = false,example = "null")
    private String ctsName1;
    @ApiModelProperty(value = "联系人1的联系方式",name = "ctsPhone1",required = false,example = "null")
    private String ctsPhone1;

    @ApiModelProperty(value = "联系人2",name = "ctsName2",required = false,example = "null")
    private String ctsName2;
    @ApiModelProperty(value = "联系人2的联系方式",name = "ctsPhone2",required = false,example = "null")
    private String ctsPhone2;

}
