package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-03-12-下午 5:58
 */
@ToString
@Getter
@Setter
@ApiModel(description = "联系人参数")
public class CtsDto {

    @ApiModelProperty(value = "联系人ID",name = "ctsId")
    private String ctsId;
    @ApiModelProperty(value = "联系人姓名",name = "ctsName")
    private String ctsName;
    @ApiModelProperty(value = "电话",name = "phone")
    private String phone;
    @ApiModelProperty(value = "用户Id",name = "userId")
    private String userId;
}
