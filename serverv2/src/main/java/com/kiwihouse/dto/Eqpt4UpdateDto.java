package com.kiwihouse.dto;

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
 * @date 2020-01-03-下午 5:29
 */
@ToString
@Getter
@Setter
@ApiModel(description = "更新设备信息参数")
public class Eqpt4UpdateDto {

    @NotBlank(message = "eqptId is not null")
    @NaturalNumber(message = "eqptId is not a natural number")
    @ApiModelProperty(value = "设备ID",name = "eqptId",required =true,position =1)
    private String eqptId;

    @ApiModelProperty(value = "设备名称",name = "eqptName",required =false)
    private String eqptName;
    @Imei
    @ApiModelProperty(value = "IMEI号",name = "imei",required =false)
    private String imei;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn",required =false)
    private String eqptSn;
    @NaturalNumber(message = "eqptType is natural number")
    @ApiModelProperty(value = "设备类型",name = "eqptType",required =false)
    private String eqptType;

    @DoubleNumber(message = "Power must be of type double")
    @ApiModelProperty(value = "电能",name = "power",required =false)
    private String power;

    @DoubleNumber(message = "voltage must be of type double")
    @ApiModelProperty(value = "电压",name = "voltage",required =false)
    private String voltage;

    @DoubleNumber(message = "electricity must be of type double")
    @ApiModelProperty(value = "电流",name = "electricity",required =false)
    private String electricity;
    @NaturalNumber
    @ApiModelProperty(value = "用户ID",name = "userId",required =false)
    private String userId;
    @NaturalNumber
    @ApiModelProperty(value = "分组Id",name = "groupId",required =false)
    private String groupId;

//    @ApiModelProperty(value = "设备详细地址",name = "eqptAddr",required =false)
//    private String eqptAddr;

    @ApiModelProperty(value = "纬度",name = "latitude",required =false)
    private String latitude;

    @ApiModelProperty(value = "经度",name = "longitude",required =false)
    private String longitude;

    @NaturalNumber
    @ApiModelProperty(value = "省市区编码",name = "code",required =false)
    private String code;

    @ApiModelProperty(value = "区域(当传递参数code时，address也必须传递)",name = "address",required =false)
    private String address;

    @ApiModelProperty(value = "设备对应的管理员ID",name = "adminId",required = false)
    private String adminId;

    @ApiModelProperty(value = "区域ID",name = "siteId",required =false,hidden = true)
    private String siteId;
    @ApiModelProperty(hidden = true)
    private String province;
    @ApiModelProperty(hidden = true)
    private String city;
    @ApiModelProperty(hidden = true)
    private String district;
    @ApiModelProperty(hidden = true)
    private String doAdminId;
}
