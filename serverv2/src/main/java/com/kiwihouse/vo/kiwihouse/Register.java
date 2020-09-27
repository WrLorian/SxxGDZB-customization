package com.kiwihouse.vo.kiwihouse;

import com.kiwihouse.common.annotation.doubleNumber.DoubleNumber;
import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-6 11:54:52
 */
@ToString
@Getter
@Setter
@ApiModel(description = "命令集合")
public class Register {

    @NaturalNumber
    @ApiModelProperty(value = "命令寄存器:0:重启设备，1:重启设备并升级，2:上报测量数据，3:上报告警信息，4:上报系统参数")
    private String reg_00;

    @NaturalNumber
    @ApiModelProperty(value = "设置电流CT,1~9999")
    private String reg_01;

    @NaturalNumber
    @ApiModelProperty(value = "设置漏电流CT,1~9999")
    private String reg_02;

    @DoubleNumber
    @ApiModelProperty(value = "设置电压上限,0.0~999.9,单位:V")
    private String reg_03;

    @DoubleNumber
    @ApiModelProperty(value = "设置电压下限,0.0~999.9,单位:V")
    private String reg_04;

    @DoubleNumber
    @ApiModelProperty(value = "设置电流上限,0.0~999.9,单位:A")
    private String reg_05;

    @NaturalNumber
    @ApiModelProperty(value = "设置功率上限,0~99999,单位:W")
    private String reg_06;

    @DoubleNumber
    @ApiModelProperty(value = "设置漏电流上限,0.0~999.9,单位:mA")
    private String reg_07;

    @DoubleNumber
    @ApiModelProperty(value = "设置漏温度上限,0.0~999.9")
    private String reg_08;

    @NaturalNumber
    @ApiModelProperty(value = "设置数据上报间隔,0~9999,单位:min")
    private String reg_09;

    @NaturalNumber
    @ApiModelProperty(value = "设置蜂鸣器开关,0:关,1:关")
    private String reg_10;

    @DoubleNumber
    @ApiModelProperty(value = "设置当前电能,0.0~99999999.9,单位:KWH")
    private String reg_11;
    
    private String addTime;

}
