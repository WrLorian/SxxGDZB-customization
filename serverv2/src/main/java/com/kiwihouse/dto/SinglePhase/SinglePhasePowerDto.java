package com.kiwihouse.dto.SinglePhase;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xin
 * @date 2020/5/2
 */
@Accessors(chain=true)
@Data
public class SinglePhasePowerDto {
    @ApiModelProperty(value = "功率")
    private double Pwr;
    @ApiModelProperty(value = "数据产生时间")
    private String AddTime;
}
