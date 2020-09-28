package com.kiwihouse.dto.OnePhases;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
public class OnePhaseMeasureDto {
	@ApiModelProperty(value = "电压", name = "curA")
    private float Vol;
    @ApiModelProperty(value = "电流", name = "curA")
    private float CurA;
    @ApiModelProperty(value = "功率", name = "pwrA")
    private float PwrA;
    @ApiModelProperty(value = "电能", name = "kwh")
    private double Kwh;
    @ApiModelProperty(value = "线温", name = "lineTemp")
    private double LineTemp;
    @ApiModelProperty(value = "漏电流", name = "leakCur")
    private float LeakCur;
    @ApiModelProperty(value = "A相功率因数", name = "pwrFctA")
    private double PwrFctA;
    @ApiModelProperty(value = "信号", name = "csp")
    private int Csp;
    @ApiModelProperty(value = "增加时间", name = "addTime")
    private String AddTime;
    private float Hz;
    private String lac;
    private String cid;
}
