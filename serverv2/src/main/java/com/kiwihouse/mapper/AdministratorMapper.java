package com.kiwihouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.AdminQueryDto;
import com.kiwihouse.dto.administrator.Administrator;
import com.kiwihouse.vo.kiwihouse.AdminAddVo;
import com.kiwihouse.vo.kiwihouse.AdminQueryVo;
import com.kiwihouse.vo.kiwihouse.AdminUpdateVo;

/**
 * @author yjzn
 * @date 2020-2-16 23:27:44
 */
@Repository
public interface AdministratorMapper {
    // add
    Integer addInfo(AdminAddVo adminAddVo);

    // query
    List<AdminQueryDto> queryFullInfo(AdminQueryVo adminQueryVo);

    AdminQueryDto queryFullInfoFirst(AdminQueryVo adminQueryVo);

    // query
    List<AdminQueryDto> queryInfo(AdminQueryVo adminQueryVo);

    //    Integer queryInfoRow(AdminQueryVo adminQueryVo);
    List<String> queryAdminIds(String adminId);

    @Select("select a.admin_name " +
            "from parent_admin as pa " +
            "inner join administrator as a on pa.parent_id=a.admin_id " +
            "where pa.admin_id=#{adminId}")
    List<String> queryParentName(String adminId);


    @Select("select admin_name " +
            "from administrator " +
            "where admin_id=#{adminId}")
    String queryName(String adminId);

    // update
    Integer updateInfo(AdminUpdateVo adminUpdateVo);

    // delete
    Integer deleteInfo(String adminId);

    @Update("update equipment set admin_id=#{adminId} where group_id=#{groupId}")
    Integer updateEqptAdmin(Integer groupId, String adminId);

    @Insert("insert into parent_admin(admin_id,parent_id) values(#{adminId},#{doAdminId})")
    Integer addParent(String adminId, String doAdminId);

    @Delete("delete from parent_admin where admin_id = #{adminId}")
    Integer deleteParent(String adminId);

    @Select("select * from administrator where admin_id=#{adminId} limit 1;")
    Administrator queryInfoById(String adminId);
}
