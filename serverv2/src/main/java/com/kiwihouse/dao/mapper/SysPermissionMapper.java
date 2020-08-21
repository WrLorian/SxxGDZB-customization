package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xin
 * @date 2020/7/14
 */
@Component
public interface SysPermissionMapper {
    List<SysPermission> getList(@Param("permission_ids")List<Integer> permission_ids);
}
