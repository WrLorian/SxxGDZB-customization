package com.kiwihouse.dto.ThreePhase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author xin
 * @date 2020/4/30
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "三相信息")
public class ThreePhaseMeasureDto {
    @ApiModelProperty(value = "A相电压", name = "volA")
    private float VolA;
    @ApiModelProperty(value = "B相电压", name = "volB")
    private float VolB;
    @ApiModelProperty(value = "C相电压", name = "volC")
    private float VolC;
    @ApiModelProperty(value = "A相电流", name = "curA")
    private float CurA;
    @ApiModelProperty(value = "B相电流", name = "curB")
    private float CurB;
    @ApiModelProperty(value = "C相电流", name = "curC")
    private float CurC;
    @ApiModelProperty(value = "A相功率", name = "pwrA")
    private float PwrA;
    @ApiModelProperty(value = "B相功率", name = "pwrB")
    private float PwrB;
    @ApiModelProperty(value = "C相功率", name = "pwrC")
    private float PwrC;
    @ApiModelProperty(value = "电能", name = "kwh")
    private double Kwh;
    @ApiModelProperty(value = "线温", name = "lineTemp")
    private double LineTemp;
    @ApiModelProperty(value = "漏电流", name = "leakCur")
    private float LeakCur;
    @ApiModelProperty(value = "A相功率因数", name = "pwrFctA")
    private double PwrFctA;
    @ApiModelProperty(value = "B相功率因数", name = "pwrFctB")
    private double PwrFctB;
    @ApiModelProperty(value = "C相功率因数", name = "pwrFctC")
    private double PwrFctC;
    @ApiModelProperty(value = "信号", name = "csp")
    private int Csp;
    @ApiModelProperty(value = "增加时间", name = "addTime")
    private String AddTime;
    private float Hz;
    private String lac;
    private String cid;
}
