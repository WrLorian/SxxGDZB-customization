package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 陈伟平
 * @date 2020-03-30-下午 1:53
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "设备上报功率数据")
public class FirePwrDto {

    @ApiModelProperty(hidden = true)
    private String pwrMsg;

    @ApiModelProperty(value = "功率数组(数组中为该时间段内每分钟的功率值)",name = "pwr")
    private List<Double> pwr;

    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;

    @ApiModelProperty(value = "时间")
    private String addTime;

}
