package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2020-3-13 09:47:48
 */
@ToString
@Getter
@Setter
public class CtsUpdateVo {

    @NotBlank(message = "ctsId is not null")
    @ApiModelProperty(value = "联系人ID",name = "ctsId")
    private String ctsId;
    @ApiModelProperty(value = "联系人姓名",name = "ctsName")
    private String ctsName;
    @ApiModelProperty(value = "电话",name = "phone")
    private String phone;
}
