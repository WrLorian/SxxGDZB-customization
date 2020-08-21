package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.NotBlank;

import com.kiwihouse.common.annotation.code.Code;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-17 09:16:40
 */
@ToString
@Getter
@Setter
@ApiModel(description = "添加区域参数")
public class SiteAddVo {

    @Code
    @NotBlank(message = "code is not null")
    @ApiModelProperty(value = "区域编码",name = "code")
    private String code;
    @NotBlank(message = "管理区域不能为空")
    @ApiModelProperty(value = "管理区域",name = "address")
    private String area;

    @ApiModelProperty(hidden = true)
    private String province;
    @ApiModelProperty(hidden = true)
    private String city;
    @ApiModelProperty(hidden = true)
    private String district;
    @ApiModelProperty(value = "管理员",name = "adminId",hidden = true)
    private String adminId;
}
