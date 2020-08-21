package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-2-16 19:57:16
 */
@ToString
@Getter
@Setter
@ApiModel("修改管理员参数")
public class AdminUpdateVo {

    @NotBlank(message = "adminId is not null")
    @ApiModelProperty(value = "管理员ID",name = "adminId",required = true)
    private String adminId;

    @ApiModelProperty(value = "管理员姓名",name = "adminName",required = false)
    private String adminName;

    @ApiModelProperty(value = "管理员登录密码",name = "password",required = false)
    private String password;

    @ApiModelProperty(value = "管理员电话",name = "adminPhone",required = false)
    private String phone;

    @ApiModelProperty(value = "上级管理员ID(只有超级管理员才能修改上级管理员ID)",name = "parentId",hidden = false)
    private Integer parentId;

    @ApiModelProperty(value = "管理员级别(1,2,3-一级、二级、三级管理员)",name = "rank",required = false)
    private String rank;

    @ApiModelProperty(value = "分组ID集合",name = "groupIds",required = false)
    private List<Integer> groupIds;
}
