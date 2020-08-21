package com.kiwihouse.vo.kiwihouse;

import com.kiwihouse.common.annotation.date.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-2-24 15:46:26
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel("数据总览查询参数")
public class DataAnalyzeVo {

    @Date
    @ApiModelProperty(value = "开始日期",name = "startTime",required = true,example = "2020-01-15")
    private String startTime;

    @Date
    @ApiModelProperty(value = "结束日期",name = "endTime",required = true,example = "2020-01-18")
    private String endTime;

    @ApiModelProperty(value = "用户Id",name = "userId",required = true)
    private String userId;

    @ApiModelProperty(value = "设备序列号",name = "eqptSn",required = false)
    private String eqptSn;

    @ApiModelProperty(value = "月数(统计过去N个月每个月用电度数)",name = "month",required = true,example = "3")
    private int month;

    @ApiModelProperty(hidden = true)
    private String adminId;

}
