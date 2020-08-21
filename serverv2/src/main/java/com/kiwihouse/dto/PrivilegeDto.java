package com.kiwihouse.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 校验管理员是否具有访问接口的权限--管理对应的权限信息
 * @author yjzn
 * @date 2019-12-24-下午 4:24
 */
@ToString
@Getter
@Setter
public class PrivilegeDto {

    private String adminId;
    private String roleId;
    private String roleName;
    private String privilegeDesc;
    private String requestUrl;
    private String requestMethod;
}
