package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.UserInfo;
import com.kiwihouse.dao.entity.relation.RUserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author xin
 * @date 2020/7/13
 */
@Component
public interface UserInfoMapper {
    UserInfo findByUsername(@Param("username") String username);

    UserInfo findByPhone(@Param("phone") String phone);

    RUserInfo rFindByUsername(@Param("username") String username);

    RUserInfo rFindByPhone(@Param("phone") String phone);

}
