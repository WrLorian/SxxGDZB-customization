package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-03-09-上午 11:00
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "火警设备上报信息")
public class ImprovedReportedDto {

    @ApiModelProperty(value = "imei号",name = "imei")
    private String imei;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "上报信息种类",name = "alarmType")
    private String alarmType;
    @ApiModelProperty(value = "数据录入时间",name = "AddTime")
    private String addTime;

    private Double csq;
    private Double cur;
    private Double vol;
    private Double pwr;
    private Double kwh;
    private Double line_temp;
    private Double pwr_fct;
    private Double leak_cur;
}
