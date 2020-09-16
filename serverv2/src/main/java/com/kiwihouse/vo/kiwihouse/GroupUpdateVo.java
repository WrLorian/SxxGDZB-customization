package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2020-3-5 16:12:11
 */
@ToString
@Getter
@Setter
@ApiModel(description = "更新分组参数")
public class GroupUpdateVo {

    @ApiModelProperty(name = "分组Id",value = "groupId")
    private String groupId;
    @ApiModelProperty(name = "分组名称(不允许重复)",value = "groupName")
    private String groupName;
    @ApiModelProperty(name = "角色ID",value = "roleId")
    private String roleId;
    @ApiModelProperty(name = "执行管理员",value = "doAdminId",hidden = true)
    private String doAdminId;
    @ApiModelProperty(name = "抄表规则",value = "cron")
    private String cron;

}
