package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-01-02-下午 2:59
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "火警设备上报信息")
public class ReportedDto {

    @ApiModelProperty(value = "具体上报信息",name = "alarmMsg")
    private String alarmMsg;
    @ApiModelProperty(value = "imei号",name = "imei")
    private String imei;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "上报信息种类",name = "alarmType")
    private String alarmType;
    @ApiModelProperty(value = "告警时读数",name = "alarmValue")
    private String alarmValue;
    @ApiModelProperty(value = "数据录入时间",name = "AddTime")
    private String addTime;
}
