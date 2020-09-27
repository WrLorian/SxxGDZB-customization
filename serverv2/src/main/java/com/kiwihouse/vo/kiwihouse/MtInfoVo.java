package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;
import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询告警信息
 * @author yjzn
 * @date 2020-1-4 11:15:16
 */
@ToString
@Getter
@Setter
@ApiModel(description = "维修记录查询参数")
public class MtInfoVo extends Basic{

    @NaturalNumber
    @ApiModelProperty(value = "维修人姓名",name = "mtName")
    private String mtName;

    @ApiModelProperty(value = "设备序列号",name = "eqptSn")
    private String eqptSn;
    
    @ApiModelProperty(value = "imei",name = "imei")
    private String imei;

    @ApiModelProperty(value = "工单类型(1:用电设备工单,2:烟雾设备工单)",name = "mtType")
    private String mtType;

    @ApiModelProperty(value = "维修状态(0-未维修，1-已维修，2-撤销，9-查询排除撤销记录)",name = "mtStatus")
    private String mtStatus;

    @ApiModelProperty(value = "页码",name = "page",required = true,example = "1")
    @NotNull(message = "page is not null")
    @Min(value = 1,message = "page is more than 1")
    private int page;

    @ApiModelProperty(value = "每页条数",name = "limit",required = true,example = "10")
    @Min(value = 1,message = "limit is more than 10")
    @NotNull(message = "limit is not null")
    private int limit;
}
