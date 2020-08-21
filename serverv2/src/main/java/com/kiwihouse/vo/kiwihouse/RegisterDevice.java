package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.NotBlank;

import com.kiwihouse.common.annotation.doubleNumber.DoubleNumber;
import com.kiwihouse.common.annotation.imei.Imei;
import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-8 18:32:37
 */
@ToString
@Getter
@Setter
@ApiModel(description = "注册信息")
public class RegisterDevice {

    @NotBlank(message = "eqptId is not null")
    @NaturalNumber
    @ApiModelProperty(value = "设备ID")
    private String eqptId;
    @NotBlank(message = "imei is not null")
    @Imei
    @ApiModelProperty(value = "设备IMEI号")
    private String imei;
    @NotBlank(message = "imsi is not null")
    @DoubleNumber
    @ApiModelProperty(value = "设备IMSI号")
    private String imsi;
    @NotBlank(message = "eqptSn is not null")
    @ApiModelProperty(value = "设备SN号")
    private String eqptSn;
    @NotBlank(message = "eqptType is not null")
    @NaturalNumber
    @ApiModelProperty(value = "设备类型,0:用电告警器,1:烟雾告警器")
    private String eqptType;
}
