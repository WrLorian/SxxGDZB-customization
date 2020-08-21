package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.NotNull;

import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;
import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-12-下午 5:48
 */
@ToString
@Getter
@Setter
@ApiModel(description = "查询联系人参数")
public class CtsQueryVo extends Basic {

    @ApiModelProperty(value = "用户Id",name = "userId")
    private String userId;
    @NaturalNumber
    @NotNull(message = "page is not null")
    @ApiModelProperty(value = "页码",name = "page",required = true,example = "1")
    private int page;
    @NaturalNumber
    @NotNull(message = "limit is not null")
    @ApiModelProperty(value = "每页条数",name = "limit",required = true,example = "10")
    private int limit;

}
