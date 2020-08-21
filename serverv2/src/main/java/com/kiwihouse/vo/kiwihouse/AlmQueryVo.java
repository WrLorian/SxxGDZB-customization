package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.Min;

import com.kiwihouse.common.annotation.date.Date;
import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;
import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-1-9 19:36:28
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "查询告警信息参数")
public class AlmQueryVo extends Basic {

    @NaturalNumber
    @ApiModelProperty(value = "区域编码",name = "code")
    private String code;

    @ApiModelProperty(value = "详细地址",name = "address")
    private String address;

    @NaturalNumber(message = "userId must be natural number")
    @ApiModelProperty(value = "用户ID",name = "userId")
    private String userId;

    @ApiModelProperty(value = "告警信息状态(-1-撤销告警，0-未处理，1-已转为工单，2-已处理)",name = "alarmStatus")
    @Min(value = -1,message = "alarmStatus is in [-1,2]")
    private String alarmStatus;
    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;

    @Date
    @ApiModelProperty(value = "开始时间",name ="startTime",required =false)
    private String startTime;

    @Date
    @ApiModelProperty(value = "结束时间",name ="endTime",required =false)
    private String endTime;

    @ApiModelProperty(value = "页码",name = "page",example = "1")
    @Min(value = 1,message = "page is more than 1")
    private int page;

    @ApiModelProperty(value = "每页条数",name = "limit",example = "10")
    @Min(value = 1,message = "limit is more than 1")
    private int limit;

    @ApiModelProperty(hidden = true)
    private String province;
    @ApiModelProperty(hidden = true)
    private String city;
    @ApiModelProperty(hidden = true)
    private String district;
}
