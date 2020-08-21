package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

/**
 * @author yjzn
 * @date 2020-1-4 11:43:27
 */
@ToString
@Getter
@Setter
@ApiModel(description = "维修记录参数")
public class MtInfoDto {

    @ApiModelProperty(value = "维修工单ID",name = "mtId")
    private String mtId;

    @ApiModelProperty(value = "维修信息",name = "msg")
    private String mtMsg;

    @ApiModelProperty(value = "维修人员姓名",name = "mtName")
    private String mtName;

    @ApiModelProperty(value = "维修人员电话",name = "mtPhone")
    private String mtPhone;

    @ApiModelProperty(value = "维修状态",name = "mtStatus")
    private String mtStatus;

    @ApiModelProperty(value = "维修时间",name = "updateTime")
    private String updateTime;


    @ApiModelProperty(value = "设备名称",name = "eqptName")
    private String eqptName;

    @ApiModelProperty(value = "设备类型",name = "eqptType")
    private String eqptType;

    @ApiModelProperty(value = "地址ID",name = "siteId")
    private String siteId;

    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;


    @ApiModelProperty(value = "告警信息ID",name = "alarmId")
    private String alarmId;

    @ApiModelProperty(value = "告警信息",name = "alarmMsg")
    private String alarmMsg;
    @ApiModelProperty(value = "告警信息map",name = "almValue")
    private HashMap<String,Object> almValue;

    @ApiModelProperty(value = "告警时间",name = "addTime")
    private String addTime;

    @ApiModelProperty(value = "省",name = "province")
    private String province;

    @ApiModelProperty(value = "市",name = "city")
    private String city;

    @ApiModelProperty(value = "区",name = "district")
    private String district;

    @ApiModelProperty(value = "地址",name = "address")
    private String address;

    @ApiModelProperty(value = "管理员ID",name = "adminId")
    private String adminId;

    @ApiModelProperty(value = "维修工单类型(1:用电设备，2:烟感设备)",name = "mtType")
    private String mtType;


}
