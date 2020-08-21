package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.kiwihouse.common.annotation.code.Code;
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
 * @date 2020-01-03-下午 3:28
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
@ApiModel(description = "查询烟感设备上报信息参数")
public class SmokeReportedQueryVo extends Basic {

    @ApiModelProperty(value = "设备序列号",name ="eqptSn",required =false)
    private String eqptSn;

    @NaturalNumber(message = "type(上报信息种类) is natural number")
    @NotBlank(message = "type is not null")
    @ApiModelProperty(value = "上报信息种类(1-自检数据,2-普通上报数据,3-紧急上报数据)",name = "type",required = true,allowableValues ="1,2,3")
    private String type;

    @NaturalNumber(message = "userId must be natural number")
    @ApiModelProperty(value = "用户ID",name = "userId")
    private String userId;

    @ApiModelProperty(value = "排序参数(自检信息默认为时间)",name = "orderBy",required = true,allowableValues = "addTime,smoke,temperature,signal,humidity,battery")
    private String orderBy;

    @ApiModelProperty(value = "顺序(自检信息默认为时间)(升序:ascending,倒叙:descending)",name = "sequence",required = true,allowableValues = "ascending,descending")
    private String sequence;


    @Date
    @ApiModelProperty(value = "开始时间",name ="startTime",required =false)
    private String startTime;

    @Date
    @ApiModelProperty(value = "结束时间",name ="endTime",required =false)
    private String endTime;

    @Code
    @ApiModelProperty(value = "区域编码(6位)",name = "code",required =false,example ="440000")
    private String code;

    @ApiModelProperty(value = "详细地址",name ="eqptAddr")
    private String eqptAddr;

    @Min(value = 1,message = "page is more than 1")
    @NotNull(message = "page is not null")
    @ApiModelProperty(value = "页码",name ="page",required =true,example ="1")
    private int page;

    @Min(value = 1,message = "limit is more than 1")
    @NotNull(message = "limit is not null")
    @ApiModelProperty(value = "每页条数",name ="limit",required =true,example = "10")
    private int limit;


    //site
    @ApiModelProperty(value = "省",name ="province",hidden = true)
    private String province;
    @ApiModelProperty(value = "市",name ="city",hidden = true)
    private String city;
    @ApiModelProperty(value = "区",name ="district",hidden = true)
    private String district;
}
