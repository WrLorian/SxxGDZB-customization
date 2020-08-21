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
 * @date 2020-02-26-下午 4:13
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel("数据总览参数")
public class Statistics {

    @ApiModelProperty(value = "该管理员总设备数量",name = "eqptNum")
    private int eqptNum;
    @ApiModelProperty(value = "该管理员火警设备数量",name = "fireNum")
    private int fireNum;
    @ApiModelProperty(value = "该管理员烟感设备数量",name = "smokeNum")
    private int smokeNum;
    @ApiModelProperty(value = "该管理员火警告警设备数量",name = "fireAlmEqptNum")
    private int fireAlmEqptNum;
    @ApiModelProperty(value = "该管理员烟感告警设备数量",name = "smokeAlmEqptNum")
    private int smokeAlmEqptNum;
    @ApiModelProperty(value = "用户总数",name = "allUserNum")
    private int allUserNum;
    @ApiModelProperty(value = "每天新增用户数",name = "newUserNum")
    private int newUserNum;
    @ApiModelProperty(value = "已处理工单数",name = "processedOrder")
    private int processedOrder;
    @ApiModelProperty(value = "未处理工单数",name = "notprocessedOrder")
    private int notprocessedOrder;
    @ApiModelProperty(value = "撤销工单数",name = "cancelledOrder")
    private int cancelledOrder;

    @ApiModelProperty(value = "当天已处理工单数",name = "processedCurrDayOrder")
    private int processedCurrDayOrder;
    @ApiModelProperty(value = "当天未处理工单数",name = "notprocessedCurrDayOrder")
    private int notprocessedCurrDayOrder;
    @ApiModelProperty(value = "当天撤销工单数",name = "cancelledCurrDayOrder")
    private int cancelledCurrDayOrder;

    @ApiModelProperty(value = "该时间段内各类告警信息对应的设备数量",name = "fireWarnMap")
    private HashMap<String, List<Integer>> fireWarnMap;
    @ApiModelProperty(value = "该时间段内各类烟感信息对应的设备数量",name = "smokeWarnMap")
    private HashMap<String, List<Integer>> smokeWarnMap;
}
