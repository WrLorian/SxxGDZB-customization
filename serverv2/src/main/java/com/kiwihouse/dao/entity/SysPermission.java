package com.kiwihouse.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xin
 * @date 2020/7/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysPermission {
    Integer permissionId;
    String name;
    String permission;
    String url;
}
