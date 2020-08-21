package com.kiwihouse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-1-13 19:03:06
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class SmokeDBInfo {

    @ApiModelProperty(value = "烟感设备上报数据ID",name = "smokeId")
    private String smokeId;
    @ApiModelProperty(value = "烟感设备上报测量数据",name = "smokeMsg")
    private String smokeMsg;
    @ApiModelProperty(value = "烟感设备上报描述信息",name = "describeMsg")
    private String describeMsg;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "上报数据状态",name = "status")
    private String status;
    @ApiModelProperty(value = "上报数据种类(1-自检信息,2-普通上报信息,3-紧急信息)",name = "type")
    private String type;
    @ApiModelProperty(value = "数据录入时间",name = "AddTime")
    private String addTime;
    @ApiModelProperty(value = "电池电量 (0-100)",name = "battery")
    private String battery;
    @ApiModelProperty(value = "烟雾浓度 (0-0.6)",name = "smoke")
    private String smoke;
    @ApiModelProperty(value = "温度 (-20.0 - 120.0 当温度出现问题时，固定报1000.0)",name = "temperature")
    private String temperature;
    @ApiModelProperty(value = "湿度 (0-100% 当湿度出现问题时，固定报1000.0)",name = "humidity")
    private String humidity;
    @ApiModelProperty(value = "信号值 (0-31 99 表示无信号)",name = "signal")
    private String signal;

    private String eqptType;
    private String eqptName;
    private String userId;
    private String siteId;
    private String userName;
    private String phone;
    private String province;
    private String city;
    private String district;
    private String address;
    private String eqptAddr;

}
