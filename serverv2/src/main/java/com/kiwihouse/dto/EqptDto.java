package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询用户信息-用户对应的设备信息
 * @author yjzn
 * @date 2019-12-19-下午 8:53
 */
@ToString
@Getter
@Setter
@ApiModel(description = "设备信息")
public class EqptDto {

    //equipment
    @ApiModelProperty(value = "设备序列号",name ="eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "设备名称",name ="eqptName")
    private String eqptName;
    @ApiModelProperty(value = "设备类型",name ="eqptType")
    private String eqptType;
    @ApiModelProperty(value = "设备状态",name ="eqptStatus")
    private String eqptStatus;
    @ApiModelProperty(value = "限定功率",name ="power")
    private String power;
    @ApiModelProperty(value = "额定电压",name ="voltage")
    private String voltage;
    @ApiModelProperty(value = "额定电流",name ="electricity")
    private String electricity;
    @ApiModelProperty(value = "设备录入时间",name ="eqptAddTime")
    private String eqptAddTime;

    //site
    @ApiModelProperty(value = "省",name ="province")
    private String province;
    @ApiModelProperty(value = "市",name ="city")
    private String city;
    @ApiModelProperty(value = "区",name ="district")
    private String district;
    @ApiModelProperty(value = "详细地址",name ="address")
    private String address;

}
