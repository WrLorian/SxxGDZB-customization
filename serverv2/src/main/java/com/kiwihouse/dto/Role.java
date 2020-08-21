package com.kiwihouse.dto;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-02-19 10:18:23
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
@ApiModel("角色信息")
public class Role{
    /**
    * 管理员ID
    */
    @ApiModelProperty(value = "管理员ID",name = "adminId")
    private Integer adminId;
    /**
    * 角色ID
    */
    @ApiModelProperty(value = "角色ID",name = "roleId")
    private Integer roleId;
    /**
    * 角色名称
    */
    @ApiModelProperty(value = "角色名称",name = "roleName")
    private String roleName;
    /**
    * 角色描述
    */
    @ApiModelProperty(value = "角色描述",name = "roleDesc")
    private String roleDesc;
    /**
    * 数据录入时间
    */
    @ApiModelProperty(value = "创建时间",name = "addTime")
    private Date addTime;

}