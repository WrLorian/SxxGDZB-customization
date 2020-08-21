package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * (Privilege)实体类
 *
 * @author makejava
 * @since 2020-02-19 10:25:55
 */
@Getter
@Setter
@ToString
@ApiModel("角色信息")
public class Privilege implements Serializable {
    private static final long serialVersionUID = 321179839476501877L;
    /**
    * 权限ID
    */
    @ApiModelProperty(value = "权限ID",name = "privilegeId")
    private Integer privilegeId;
    /**
    * 权限描述
    */
    @ApiModelProperty(value = "权限描述",name = "privilegeDesc")
    private String privilegeDesc;
    /**
    * 对应功能请求URL
    */
    @ApiModelProperty(value = "请求URL",name = "requestUrl")
    private String requestUrl;
    /**
    * 对应功能请求方法，必须全部大写(GET、POST)
    */
    @ApiModelProperty(value = "请求方法",name = "requestMethod")
    private String requestMethod;
    /**
    * 数据录入时间
    */
    @ApiModelProperty(value = "创建时间",name = "addTime")
    private Date addTime;
}