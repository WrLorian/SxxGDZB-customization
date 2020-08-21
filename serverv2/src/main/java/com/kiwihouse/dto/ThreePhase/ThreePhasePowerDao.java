package com.kiwihouse.dto.ThreePhase;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xin
 * @date 2020/5/1
 */
@Accessors(chain=true)
@Data
public class ThreePhasePowerDao {
    @ApiModelProperty(value = "数据数量")
    private int Num;
    @ApiModelProperty(value = "IMEI")
    private String Imei;
    @ApiModelProperty(value = "A相功率")
    private String PwrA;
    @ApiModelProperty(value = "B相功率")
    private String PwrB;
    @ApiModelProperty(value = "C相功率")
    private String PwrC;
    @ApiModelProperty(value = "数据产生时间")
    private String AddTime;

}

