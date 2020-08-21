package com.kiwihouse.vo.kiwihouse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2020-02-27-上午 11:46
 */
@ToString
@Setter
@Getter
@ApiModel(description = "登录参数")
public class Login {

    @NotBlank(message = "phone is not null")
    @ApiModelProperty(example = "13027300000")
    private String phone;
    @NotBlank(message = "password is not null")
    @ApiModelProperty(example = "jZae727K08KaOmKSgOaGzww/XVqGr/PKEgIMkjrcbJI=")
    private String password;

    @ApiModelProperty(value = "1管理员，2普通用户", example = "1")
    private String userType;
}
