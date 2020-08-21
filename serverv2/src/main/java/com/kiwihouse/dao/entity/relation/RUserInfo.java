package com.kiwihouse.dao.entity.relation;

import com.kiwihouse.dao.entity.SysPermission;
import com.kiwihouse.dao.entity.UserInfo;
import lombok.*;

import java.util.List;

/**
 * @author xin
 * @date 2020/7/13
 */

@AllArgsConstructor
@NoArgsConstructor

public class RUserInfo extends UserInfo {

    @Getter
    @Setter
    String roleName;
    @Getter
    @Setter
    List<Integer> permissionIds;
    @Getter
    @Setter
    List<SysPermission> permissionArr;


}
