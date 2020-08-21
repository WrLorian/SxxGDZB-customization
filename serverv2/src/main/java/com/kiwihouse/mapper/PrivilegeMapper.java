package com.kiwihouse.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.PorRoleDto;
import com.kiwihouse.dto.Privilege;
import com.kiwihouse.dto.PrivilegeDto;
import com.kiwihouse.dto.ProPrivilegeDto;
import com.kiwihouse.dto.Role;
import com.kiwihouse.vo.kiwihouse.RoleAddVo;
import com.kiwihouse.vo.kiwihouse.RoleUpdateVo;

/**
 * @author yjzn
 * @date 2019-12-24-下午 2:28
 */
@Repository
public interface PrivilegeMapper {

    //intercept
    ArrayList<PrivilegeDto> verify(String adminId);

    //query POR information
    ArrayList<PorRoleDto> queryPORInfo(HashMap<String, String> map);
    Integer queryPORInfoRow(HashMap<String, String> map);

    //query privilege information
    List<ProPrivilegeDto> queryInfo();
    Integer queryInfoRow();

    //query roleId by adminId
    @Select("select `rank` from administrator where admin_id=#{adminId}")
    String isTopMg(String adminId);

    @Select("select ar.admin_id,ar.role_id,role.role_name,role.role_desc,role.add_time " +
            "from admin_role as ar " +
            "inner join role on ar.role_id=role.role_id " +
            "where ar.admin_id=#{adminId}")
    List<Role> queryROA(HashMap<String, String> map);
    @Select("select count(*) " +
            "from admin_role as ar " +
            "inner join role on ar.role_id=role.role_id " +
            "where ar.admin_id=#{adminId}")
    Integer queryROARow(HashMap<String, String> map);
    @Select("select ar.admin_id,ar.role_id,rp.privilege_id,rp.add_time " +
            "from admin_role as ar " +
            "left join role_privilege as rp on ar.role_id=rp.role_id " +
            "where ar.admin_id=#{adminId}")
    List<Privilege> queryPOA(HashMap<String, String> map);
    @Select("select count(*) " +
            "from admin_role as ar " +
            "left join role_privilege as rp on ar.role_id=rp.role_id " +
            "where ar.admin_id=#{adminId}")
    Integer queryPOARow(HashMap<String, String> map);

    //创建角色并赋权
    Integer addRole(RoleAddVo roleAddVo);
    Integer addRolePrivilege(ArrayList<HashMap<String,String>> list);
    //为管理员赋予角色
    Integer addAdminRole(ArrayList<HashMap<String,String>> list);

    //删除该角色的全部权限
    @Delete("delete from role_privilege where role_id=#{roleId}")
    Integer deleteRolePrivilege(String roleId);

    //修改角色信息-roleName、roleDesc
    Integer updateRole(RoleUpdateVo roleUpdateVo);
    //删除用户角色
    @Delete("delete from admin_role where admin_id=#{adminId}")
    Integer deleteAdminRole(String adminId);
    //删除角色
    @Delete("delete from role where role_id=#{roleId}")
    Integer deleteRole(String roleId);
}
