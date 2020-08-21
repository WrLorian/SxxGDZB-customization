package com.kiwihouse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-02-26-上午 10:48
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class ImprovedWarnMsgDto {


    private String cur;
    private String leak;
    private String vol;
    private String temp;
    private String overload;

    private String curValue;
    private String leakValue;
    private String volValue;
    private String tempValue;
    private String overloadValue;

    @ApiModelProperty(value = "imei号",name = "imei")
    private String imei;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "上报信息种类",name = "alarmType")
    private String alarmType;
    @ApiModelProperty(value = "数据录入时间",name = "AddTime")
    private String addTime;
}
