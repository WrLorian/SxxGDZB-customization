package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author yjzn
 * @date 2020-2-15 22:13:04
 */
@ToString
@Getter
@Setter
@ApiModel("查询管理员参数")
public class AdminQueryVo{

    @ApiModelProperty(value = "查询数据类型(1:查询个人信息,2:查询下属管理员信息,超级管理员为全部信息)",name ="type",required = true)
    private String type;

    @ApiModelProperty(value = "管理员电话号码(type=2时使用)",name ="phone",required = false)
    private String phone;

    @ApiModelProperty(value = "页码(type=2时使用)",name = "page",required =true,example ="1")
    @NotNull(message = "page is not null")
    @Min(value = 1,message = "page最小为1")
    private int page;

    @ApiModelProperty(value = "每页条数(type=2时使用)",name = "limit",required =true,example ="10")
    @NotNull(message = "limit is not null")
    @Min(value = 1,message = "limit最小为10")
    private int limit;

    @ApiModelProperty(value = "上级管理员Id",name ="parentId",hidden = true)
    private String parentId;
    @ApiModelProperty(value = "管理员Id",name ="adminId",hidden = true)
    private String adminId;
}
