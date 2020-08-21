package com.kiwihouse.dto.equipt;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
@ApiModel(description = "查询设备信息参数(设备监控模块)")
public class Equipt {
	@ApiModelProperty(value = "设备序列号(SN号)")
    private String eqptSn;
	@ApiModelProperty(value = "与设备一一对应详细地址")
    private String eqptAddr;
	@ApiModelProperty(value = "设备名称")
    private String eqptName;
	@ApiModelProperty(value = "设备在线状态(9000:在线,9001:掉线)")
    private String eqptStatus;
	@ApiModelProperty(value = "省",name = "province")
    private String province;
    @ApiModelProperty(value = "市",name = "city")
    private String city;
    @ApiModelProperty(value = "区",name = "district")
    private String district;
    @ApiModelProperty(value = "设备IMEI号")
    private String imei;
	
}
