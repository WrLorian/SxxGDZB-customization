package com.kiwihouse.mapper;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.UserDto;

/**
 * @author yjzn
 * @date 2020-02-27-下午 3:24
 */
@Repository
public interface LoginMapper {
    @Select("select password from administrator where phone=#{phone}")
    String queryAdminInfo(String phone);

    @Select("select user_name,phone,user_id,password from user where phone=#{phone} and password=#{password} limit 1;")
    UserDto queryUserInfo(String phone,String password);

    @Select("select phone from administrator where admin_id=#{adminId}")
    String queryAdminPhone(String adminId);
}
