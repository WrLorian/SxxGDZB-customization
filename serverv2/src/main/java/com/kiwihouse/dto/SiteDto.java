package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-17 11:57:42
 */
@ToString
@Getter
@Setter
@ApiModel(description = "查询区域返回参数")
public class SiteDto {

    @ApiModelProperty(value = "区域ID",name = "区域Id")
    private String siteId;
    @ApiModelProperty(value = "省",name = "province")
    private String province;
    @ApiModelProperty(value = "市",name = "city")
    private String city;
    @ApiModelProperty(value = "区",name = "district")
    private String district;
    @ApiModelProperty(value = "区域名称",name = "area")
    private String area;
    @ApiModelProperty(value = "区域添加时间",name = "addTime")
    private String addTime;
    @ApiModelProperty(value = "管理员名称",name = "adminName")
    private String adminName;
    @ApiModelProperty(value = "管理员Id",name = "roleId")
    private String roleId;

}
