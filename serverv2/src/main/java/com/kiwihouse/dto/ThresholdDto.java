package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-18-下午 5:00
 */
@ToString
@Getter
@Setter
@ApiModel(description = "火警设备预警阀值")
public class ThresholdDto {
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "过压阀值",name = "volH",example = "265")
    private Double volH;
    @ApiModelProperty(value = "欠压阀值",name = "volL",example = "185")
    private Double volL;
    @ApiModelProperty(value = "漏电流阀值",name = "leakH",example = "20")
    private Double leakH;
    @ApiModelProperty(value = "电流阀值",name = "curH",example = "10")
    private Double curH;
    @ApiModelProperty(value = "过载阀值",name = "pwrH",example = "2000")
    private Double pwrH;
    @ApiModelProperty(value = "温度阀值",name = "tempH",example = "30")
    private Double tempH;
    @ApiModelProperty(value = "更新时间",name = "updateTime")
    private String updateTime;
    @ApiModelProperty(value = "录入时间",name = "addTime")
    private String addTime;
    @ApiModelProperty(value = "主键ID",name = "thresholdId")
    private String thresholdId;
}
