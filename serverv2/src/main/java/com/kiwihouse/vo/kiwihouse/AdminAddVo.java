package com.kiwihouse.vo.kiwihouse;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-1-10 13:59:44
 */
@ToString
@Getter
@Setter
@ApiModel("录入管理员参数")
public class AdminAddVo {

    @NotBlank(message = "adminName is not null")
    @ApiModelProperty(value = "管理员姓名",name = "adminName",required = true)
    private String adminName;

    @NotBlank(message = "password is not null")
    @ApiModelProperty(value = "管理员登录密码",name = "password",required = true)
    @Length(message = "密码至少6位",min = 6)
    private String password;

    @NotBlank(message = "adminPhone is not null")
    @ApiModelProperty(value = "管理员电话",name = "adminPhone",required = true)
    private String phone;

    @NotBlank(message = "rank is not null")
    @ApiModelProperty(value = "管理员级别(1,2,3-一级、二级、三级管理员)",name = "rank",required = true)
    private String rank;

    @ApiModelProperty(value = "分组ID集合",name = "groupIds",required = true)
    private List<Integer> groupIds;


    @Null
    @ApiModelProperty(value = "上级管理员ID",name = "parentId",hidden = true)
    private String parentId;

    @ApiModelProperty(value = "管理员ID",name = "adminId",hidden = true)
    private String adminId;
    @ApiModelProperty(hidden = true)
    private String addTime;




}
