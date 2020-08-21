package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-2-18 09:29:32
 */
@ToString
@Getter
@Setter
@ApiModel("赋予管理员角色")
public class AdminRoleAddVo {

    @ApiModelProperty(value = "管理员ID",name = "adminId",required = true)
    private String adminId;
    @ApiModelProperty(value = "角色集合(三级用户可对应多个角色)",name = "roleIdList",required = true)
    private List<String> roleIdList;
}
