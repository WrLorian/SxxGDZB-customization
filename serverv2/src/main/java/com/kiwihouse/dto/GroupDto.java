package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-05-下午 4:00
 */
@ToString
@Getter
@Setter
@ApiModel(description = "查询分组返回参数")
public class GroupDto {

    @ApiModelProperty(value = "分组Id",name = "groupId")
    private String groupId;
    @ApiModelProperty(value = "分组名称",name = "groupName")
    private String groupName;
    @ApiModelProperty(value = "抄表周期",name = "cron")
    private String cron;
    @ApiModelProperty(value = "添加时间",name = "addTime")
    private String addTime;
    @ApiModelProperty(value = "角色标识",name = "code")
    private String code;
    @ApiModelProperty(value = "所属管理员姓名",name = "adminName")
    private String adminName;

    @ApiModelProperty(value = "抄表月份",name = "months")
    private Object months;
    @ApiModelProperty(value = "抄表日期",name = "date")
    private String date;
    @ApiModelProperty(value = "抄表时间",name = "time")
    private String time;
    @ApiModelProperty(value = "分组设备ID",name = "time")
    private String eqptIds;
}
