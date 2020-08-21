package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.AuthRoleMenu;

public interface AuthRoleMenuMapper {
    int insert(AuthRoleMenu record);

    int insertSelective(AuthRoleMenu record);
}