package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2019-12-20-下午 6:22
 */
@ToString
@Getter
@Setter
public class UserUpdateVo {

    //用户信息
    @ApiModelProperty(value = "用户ID",name = "userId",required = true,example = "1")
    @Min(value = 1,message = "userId 最小为1")
    @NotBlank(message = "userId is not null")
    private String userId;

    @ApiModelProperty(value = "用户名",name = "userName",required = false,example = "张三")
    private String userName;

    @ApiModelProperty(value = "密码",name = "password",required = false,example = "sha256(123456)")
    private String password;

    @ApiModelProperty(value = "电话号码",name = "phone",required = false,example = "13027301010")
    private String phone;

    @ApiModelProperty(value = "用户头像",name = "imgUrl",required = false,example = "http://localhost:8090/pciture.jpg")
    private String imgUrl;

    //联系人信息
    @ApiModelProperty(value = "联系人1的ID",name = "ctsId1",required = false,example = "null")
    private String ctsId1;
    @ApiModelProperty(value = "联系人1",name = "ctsName1",required = false,example = "null")
    private String ctsName1;
    @ApiModelProperty(value = "联系人1的联系方式",name = "ctsPhone1",required = false,example = "null")
    private String ctsPhone1;

    @ApiModelProperty(value = "联系人2的ID",name = "ctsId2",required = false,example = "null")
    private String ctsId2;
    @ApiModelProperty(value = "联系人2",name = "ctsName2",required = false,example = "null")
    private String ctsName2;
    @ApiModelProperty(value = "联系人2的联系方式",name = "ctsPhone2",required = false,example = "null")
    private String ctsPhone2;
}
