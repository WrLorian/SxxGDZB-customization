package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-3-5 16:12:11
 */
@ToString
@Getter
@Setter
@ApiModel(description = "添加分组参数")
public class GroupAddVo {

    @ApiModelProperty(name = "分组名称(不允许重复)",value = "groupName")
    private String groupName;
    @ApiModelProperty(hidden = true)
    private String roleId;
    @ApiModelProperty(hidden = true)
    private String cron;
    @ApiModelProperty(hidden = true)
    private String groupId;
    @ApiModelProperty(hidden = true)
    private String addTime;

}
