package com.kiwihouse.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2019-12-30-下午 1:47
 */
@ToString
@Setter
@Getter
@ApiModel(description = "查询设备信息参数")
public class EqptInfoDto {

    @ApiModelProperty(value = "省")
    private String province;
    @ApiModelProperty(value = "市")
    private String city;
    @ApiModelProperty(value = "区")
    private String district;
    @ApiModelProperty(value = "区下级地址")
    private String address;
    @ApiModelProperty(value = "与设备一一对应详细地址")
    private String eqptAddr;
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "系统生成设备ID")
    private String eqptId;
    @ApiModelProperty(value = "onenet平台生成设备ID")
    private String deviceId;
    @ApiModelProperty(value = "设备序列号(SN号)")
    private String eqptSn;
    @ApiModelProperty(value = "设备IMEI号")
    private String imei;
    @ApiModelProperty(value = "设备IMSI号")
    private String imsi;
    @ApiModelProperty(value = "设备名称")
    private String eqptName;
    @ApiModelProperty(value = "设备类型(0:用电告警器,1:烟雾告警器)")
    private String eqptType;
    @ApiModelProperty(value = "设备是否注册到onenet平台")
    private String register;
    @ApiModelProperty(value = "设备额定功率")
    private String power;
    @ApiModelProperty(value = "设备限定电压")
    private String voltage;
    @ApiModelProperty(value = "设备限定电流")
    private String electricity;
    @ApiModelProperty(value = "设备在线状态(9000:在线,9001:掉线)")
    private String eqptStatus;
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @ApiModelProperty(value = "地址ID")
    private String siteId;
    @ApiModelProperty(value = "管理员ID")
    private String adminId;
    @ApiModelProperty(value = "设备添加时间")
    private String addTime;
    @ApiModelProperty(value = "管理员ID")
    private String roleId;
//    不知道干啥用的
//    @ApiModelProperty(value = "")
//    private String alarmMsg;
    @ApiModelProperty(value = "用户姓名")
    private String userName;
    @ApiModelProperty(value = "用户电话")
    private String phone;
    @ApiModelProperty(value = "设备分组ID")
    private String groupId;
    @ApiModelProperty(value = "设备分组名称")
    private String groupName;

    @ApiModelProperty(value = "设备电能")
    private String kwh;
    @ApiModelProperty(value = "设备上次抄表时间")
    private String LeastCopyTime;

    @ApiModelProperty(value = "图片")
    private List<String> imgs;

}
