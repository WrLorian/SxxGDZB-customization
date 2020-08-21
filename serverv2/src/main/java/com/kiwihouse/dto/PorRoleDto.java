package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 查询角色权限--角色信息
 * por: privilege of role
 * @author yjzn
 * @date 2019-12-25-下午 5:40
 */
@ToString
@Getter
@Setter
@ApiModel
public class PorRoleDto {

    @ApiModelProperty(value = "角色Id")
    private String roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色描述")
    private String roleDesc;
    @ApiModelProperty(value = "角色对应的前端路有树")
    private String routerTree;

    private List<ProPrivilegeDto> list;

}
