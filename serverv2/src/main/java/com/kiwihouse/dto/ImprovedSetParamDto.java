package com.kiwihouse.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-03-09-下午 6:57
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class ImprovedSetParamDto {

    private Double CT;
    private Double Beep;
    private Double volH;
    private Double leakH;
    private Double curH;
    private Double pwrH;
    private Double TempH;
    private Double lCT;
    private Double volL;
    private Double Interval;

    @ApiModelProperty(value = "imei号",name = "imei")
    private String imei;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "上报信息种类",name = "alarmType")
    private String alarmType;
    @ApiModelProperty(value = "数据录入时间",name = "AddTime")
    private String addTime;
}
