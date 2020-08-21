package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import java.util.HashMap;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-02-28-下午 4:49
 */
@Accessors(chain=true)
@ToString
@Getter
@Setter
@ApiModel
public class Analyze {

    @ApiModelProperty(value = "每一天电流最大值列表")
    private List<Double> cur;
    @ApiModelProperty(value = "每月电能总和列表")
    private List<Double> kwh;
    @ApiModelProperty(value = "每一天功率最大值列表")
    private List<Double> power;
    @ApiModelProperty(value = "每一天漏电流最大值列表")
    private List<Double> leak;
    @ApiModelProperty(value = "每一天线温最大值列表")
    private List<Double> lineTemp;

    @ApiModelProperty(value = "电流峰值那天的电流值")
    private List<Double> curDay4;
    @ApiModelProperty(value = "电流峰值那天的功率值")
    private List<Double> powerDay4;
    @ApiModelProperty(value = "电流峰值那天的漏电流值")
    private List<Double> leakDay4;
    @ApiModelProperty(value = "电流峰值那天的线温值")
    private List<Double> lineTempDay4;

    @ApiModelProperty(value = "功率峰值那天的电流值")
    private List<Double> curDay1;
    @ApiModelProperty(value = "功率峰值那天的功率值")
    private List<Double> powerDay1;
    @ApiModelProperty(value = "功率峰值那天的漏电流值")
    private List<Double> leakDay1;
    @ApiModelProperty(value = "功率峰值那天的线温值")
    private List<Double> lineTempDay1;

    @ApiModelProperty(value = "漏电流峰值那天的电流值")
    private List<Double> curDay2;
    @ApiModelProperty(value = "漏电流峰值那天的功率值")
    private List<Double> powerDay2;
    @ApiModelProperty(value = "漏电流峰值那天的漏电流值")
    private List<Double> leakDay2;
    @ApiModelProperty(value = "漏电流峰值那天的线温值")
    private List<Double> lineTempDay2;

    @ApiModelProperty(value = "漏电流峰值那天的电流值")
    private List<Double> curDay3;
    @ApiModelProperty(value = "漏电流峰值那天的功率值")
    private List<Double> powerDay3;
    @ApiModelProperty(value = "漏电流峰值那天的漏电流值")
    private List<Double> leakDay3;
    @ApiModelProperty(value = "漏电流峰值那天的线温值")
    private List<Double> lineTempDay3;

}
