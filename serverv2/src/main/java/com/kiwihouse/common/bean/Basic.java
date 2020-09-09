package com.kiwihouse.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-19 15:15:12
 */
@ToString
@Getter
@Setter
@ApiModel(description = "基础信息类")
public class Basic {

    @ApiModelProperty(value = "角色Id",name ="roleId",hidden = true)
    private String roleId;
}
