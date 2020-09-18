package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-9 20:04:10
 */
@ToString
@Getter
@Setter
@ApiModel(description = "告警设备信息")
public class AlarmEqptDto {

    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    @ApiModelProperty(value = "设备imei号",name = "imei")
    private String imei;
    @ApiModelProperty(value = "设备类型",name = "eqptType")
    private String eqptType;
    @ApiModelProperty(value = "设备名称",name = "eqptName")
    private String eqptName;
    @ApiModelProperty(value = "用户ID",name = "userId")
    private String userId;
    @ApiModelProperty(value = "地址ID",name = "siteId")
    private String siteId;
    
    @ApiModelProperty(value = "用户姓名",name = "userName")
    private String userName;
    @ApiModelProperty(value = "用户电话",name = "userPhone")
    private String userPhone;
    @ApiModelProperty(value = "联系人姓名",name = "ctsName")
    private String ctsName;
    @ApiModelProperty(value = "联系人电话",name = "ctsPhone")
    private String ctsPhone;

    @ApiModelProperty(value = "省",name = "province")
    private String province;
    @ApiModelProperty(value = "市",name = "city")
    private String city;
    @ApiModelProperty(value = "区",name = "district")
    private String district;
    @ApiModelProperty(value = "详细地址",name = "eqptAddr")
    private String eqptAddr;

    @ApiModelProperty(value = "告警信息ID",name = "alarmId")
    private String alarmId;
    @ApiModelProperty(value = "告警信息",name = "alarmMsg")
    private String alarmMsg;
    @ApiModelProperty(value = "告警状态()",name = "alarmStatus")
    private String alarmStatus;
    @ApiModelProperty(value = "告警时间",name = "addTime")
    private String addTime;

}
