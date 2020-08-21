package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-2-19 14:39:59
 */
@Getter
@Setter
@ToString
@ApiModel(description = "修改角色参数")
public class RoleUpdateVo {

    @NotBlank(message = "roleId is not null")
    @ApiModelProperty(value = "角色ID",name ="roleId",required =true)
    private String roleId;
    @ApiModelProperty(value = "角色名称",name = "roleName",required = false)
    private String roleName;
    @ApiModelProperty(value = "角色描述",name = "roleDesc",required = false)
    private String roleDesc;
    @ApiModelProperty(value = "路由树",name = "routerTree",required = false)
    private String routerTree;
    @ApiModelProperty(value = "角色对应的权限ID集合",name = "privilegeList",required = false)
    private List<String> privilegeList;
}
