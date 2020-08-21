package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-18-上午 10:50
 */
@ToString
@Getter
@Setter
@ApiModel(description = "告警的设备数和告警数")
public class AlmAndEqptNumDto {

    @ApiModelProperty(value = "告警数",name = "almNum")
    private Integer almNum;
    @ApiModelProperty(value = "告警设备数",name = "eqptNum")
    private Integer eqptNum;
}
