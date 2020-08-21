package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-18-上午 11:34
 */
@ToString
@Getter
@Setter
@ApiModel(description = "不同类型告警信息对应的设备数和告警信息数")
public class AlmAndEqptNumReturnDto {

    @ApiModelProperty(value = "已转为订单的告警",name = "almToOrder")
    private AlmAndEqptNumDto almToOrder;
    @ApiModelProperty(value = "已处理的告警",name = "almProcessed")
    private AlmAndEqptNumDto almProcessed;
    @ApiModelProperty(value = "未处理的告警",name = "almUnprocessed")
    private AlmAndEqptNumDto almUnprocessed;

    @ApiModelProperty(value = "已撤销订单",name = "mtCancel")
    private Integer mtCancel;
    @ApiModelProperty(value = "已处理订单",name = "mtProcessed")
    private Integer mtProcessed;
    @ApiModelProperty(value = "未处理订单",name = "mtUnprocessed")
    private Integer mtUnprocessed;

    @ApiModelProperty(value = "设备数量",name = "totalNum")
    private Integer totalNum;
    @ApiModelProperty(value = "火警设备数",name = "fireNum")
    private Integer fireNum;
    @ApiModelProperty(value = "烟感设备数",name = "smokeNum")
    private Integer smokeNum;

}
