package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2019-12-19-下午 4:38
 */
@ToString
@Getter
@Setter
@ApiModel("用户数据查询表单")
public class UserQueryVo extends Basic {

    @ApiModelProperty(value = "用户名",name = "userName",required = false)
    private String userName;

    @ApiModelProperty(value = "电话号码",name = "phone",required = false)
    private String phone;

    @Min(1)
    @NotNull(message = "page is not null")
    @ApiModelProperty(value = "页码",name = "page",required = true,example = "1")
    private int page;

    @Min(1)
    @NotNull(message = "limit is not null")
    @ApiModelProperty(value = "每页条数",name = "limit",required = true,example = "10")
    private int limit;
}
