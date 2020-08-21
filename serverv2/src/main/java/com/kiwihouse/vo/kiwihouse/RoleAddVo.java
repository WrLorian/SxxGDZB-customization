package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-2-17 15:39:28
 */
@Getter
@Setter
@ToString
@ApiModel(description = "创建角色参数")
public class RoleAddVo {

    @ApiModelProperty(value = "角色名称",name = "roleName",required = true)
    private String roleName;
    @ApiModelProperty(value = "角色描述",name = "roleDesc",required = true)
    private String roleDesc;
    @ApiModelProperty(value = "角色对应的权限ID集合",name = "privilegeList",required = true)
    private List<String> privilegeList;
    @ApiModelProperty(value = "路由树",name = "routerTree")
    private String routerTree;
    @ApiModelProperty(hidden = true)
    private String roleId;
    @ApiModelProperty(hidden = true)
    private String addTime;

}
