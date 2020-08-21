package com.kiwihouse.dto.ThreePhase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author xin
 * @date 2020/5/1
 */
@Accessors(chain = true)
@Data
@ApiModel(description = "三相最大功率")
public class ThreePhasePowerDto {

    @ApiModelProperty(value = "IMEI",name = "imei")
    private String Imei;
    @ApiModelProperty(value = "A相功率",name = "pwrA")
    private double PwrA;
    @ApiModelProperty(value = "B相功率",name = "pwrB")
    private double PwrB;
    @ApiModelProperty(value = "C相功率",name = "pwrC")
    private double PwrC;
    @ApiModelProperty(value = "最大功率",name = "maxPower")
    private double MaxPower;
    @ApiModelProperty("数据产生时间")
    private String AddTime;
}
