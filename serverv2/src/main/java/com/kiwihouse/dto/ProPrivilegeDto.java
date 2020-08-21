package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询角色权限--权限信息
 * 展示全部权限信息--权限信息
 * @author yjzn
 * @date 2019-12-25-下午 8:34
 */
@ToString
@Getter
@Setter
@ApiModel
public class ProPrivilegeDto {

    @ApiModelProperty(value = "权限ID")
    private String privilegeId;
    @ApiModelProperty(value = "权限描述")
    private String privilegeDesc;
    @ApiModelProperty(value = "权限url路径")
    private String requestUrl;
    @ApiModelProperty(value = "url路径对应http请求方法")
    private String requestMethod;
}
