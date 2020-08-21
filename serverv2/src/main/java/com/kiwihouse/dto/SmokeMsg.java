package com.kiwihouse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 烟感设备上报三类数据抽象出的父类
 * @author yjzn
 * @date 2020-1-13 17:23:23
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class SmokeMsg {

    @ApiModelProperty(value = "烟感设备上报数据ID",name = "smokeId")
    private String smokeId;
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

    @ApiModelProperty(value = "电量阈值(0-100)",name = "batteryThreshold")
    private String batteryThreshold;
    @ApiModelProperty(value = "烟雾报警阈值(0-0.6)",name = "smokeThreshold")
    private String smokeThreshold;
    @ApiModelProperty(value = "温度报警阈值(-20.0-120.0)",name = "temperatureThreshold")
    private String temperatureThreshold;
    @ApiModelProperty(value = "心跳上报周期(1-4294967295)/秒",name = "normalReportRate")
    private String normalReportRate;
    @ApiModelProperty(value = "紧急上报周期(1-65535)",name = "urgentReportRate")
    private String urgentReportRate;
    @ApiModelProperty(value = "蜂鸣器鸣响模式",name = "beepMode")
    private String beepMode;

}
