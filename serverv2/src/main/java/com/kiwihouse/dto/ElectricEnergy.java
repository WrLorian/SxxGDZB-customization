package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.io.Serializable;

/**
 * (ElectricEnergy)实体类
 *
 * @author makejava
 * @since 2020-02-25 14:10:04
 */
@ToString
@Getter
@Setter
@ApiModel("查询电能参数")
public class ElectricEnergy{
    /**
    * 电能ID
    */
    @ApiModelProperty(value = "电能ID",name = "kwhId")
    private Integer kwhId;
    /**
    * 电能/度
    */
    @ApiModelProperty(value = "电能/度",name = "kwh")
    private Object kwh;
    /**
    * 设备序列号
    */
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    /**
     * 区域(组)ID
     */
    @ApiModelProperty(value = "组ID",name = "groupId")
    private Integer groupId;
    /**
     * 第多少次抄表
     */
    @ApiModelProperty(value = "抄表批次(第多少次抄表)",name = "times")
    private Integer times;
    /**
     * 数据录入时间
     */
    @ApiModelProperty(value = "抄表时间",name = "addTime")
    private String addTime;
}