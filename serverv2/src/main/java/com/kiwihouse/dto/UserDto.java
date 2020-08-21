package com.kiwihouse.dto;

import java.util.List;

import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询用户信息--用户信息
 * @author yjzn
 * @date 2019-12-19-下午 4:47
 */
@ToString
@Getter
@Setter
@ApiModel(description = "用户信息")
public class UserDto extends Basic {

    //用户信息
    @ApiModelProperty(value = "用户ID",name ="userId")
    private String userId;
    @ApiModelProperty(value = "用户名",name ="userName")
    private String userName;
    @ApiModelProperty(value = "电话号码",name ="phone")
    private String phone;
    @ApiModelProperty(value = "用户头像",name ="imgUrl")
    private String imgUrl;
    @ApiModelProperty(value = "设备数量",name ="eqptNum")
    private String eqptNum;
    @ApiModelProperty(value = "用户录入时间",name ="userAddTime")
    private String userAddTime;

    //联系人信息
    @ApiModelProperty(value = "联系人1的ID",name ="ctsId1")
    private String ctsId1;
    @ApiModelProperty(value = "联系人1的姓名",name ="ctsName1")
    private String ctsName1;
    @ApiModelProperty(value = "联系人1的ID电话",name ="ctsPhone1")
    private String ctsPhone1;
    @ApiModelProperty(value = "联系人2的ID",name ="ctsId2")
    private String ctsId2;
    @ApiModelProperty(value = "联系人2的姓名",name ="ctsName2")
    private String ctsName2;
    @ApiModelProperty(value = "联系人2的电话",name ="ctsPhone2")
    private String ctsPhone2;

    private List<EqptDto> list;

}
