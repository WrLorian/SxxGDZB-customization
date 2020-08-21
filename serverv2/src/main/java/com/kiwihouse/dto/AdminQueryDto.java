package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.annotations.Result;

import java.util.List;

/**
 * @author yjzn
 * @date 2020-1-19 15:15:12
 */
@ToString
@Getter
@Setter
@ApiModel(description = "管理员信息")
public class AdminQueryDto {

    @ApiModelProperty(value = "管理员ID", name = "adminId")
    private String adminId;
    @ApiModelProperty(value = "管理员姓名", name = "adminName")
    private String adminName;
    @ApiModelProperty(value = "管理员电话", name = "phone")
    private String phone;
    @ApiModelProperty(value = "数据录入时间", name = "addTime")
    private String addTime;
    @ApiModelProperty(value = "上级管理员ID", name = "parentId")
    private String parentId;
    @ApiModelProperty(value = "上级管理员姓名", name = "parenAdminName")
    private String parentAdminName;
    @ApiModelProperty(value = "管理员级别", name = "rank")
    private String rank;
    @ApiModelProperty(value = "角色ID", name = "roleId")
    private List<String> roleId;
    @ApiModelProperty(value = "权限ID", name = "privilegeId")
    private List<String> privilegeId;
    @ApiModelProperty(value = "路由树", name = "routerTree")
    private List<String> routerTree;
    @ApiModelProperty(value = "分组ID", name = "groupIds")
    private List<Integer> groupIds;
    
    @ApiModelProperty(hidden = true)
    private String sessionId;

}
