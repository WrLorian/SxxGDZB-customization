package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2020-03-13-上午 9:29
 */
@ToString
@Getter
@Setter
public class CtsAddVo {

    @ApiModelProperty(value = "联系人ID",name = "ctsId",hidden = true)
    private String ctsId;
    @NotBlank(message = "ctsName is not null")
    @ApiModelProperty(value = "联系人姓名",name = "ctsName")
    private String ctsName;
    @NotBlank(message = "phone is not null")
    @ApiModelProperty(value = "电话",name = "phone")
    private String phone;
    @NotBlank(message = "userId is not null")
    @ApiModelProperty(value = "用户Id",name = "userId")
    private String userId;
}
