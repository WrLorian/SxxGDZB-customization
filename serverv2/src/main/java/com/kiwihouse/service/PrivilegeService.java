package com.kiwihouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.Properties;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dto.PorRoleDto;
import com.kiwihouse.dto.Privilege;
import com.kiwihouse.dto.PrivilegeDto;
import com.kiwihouse.dto.ProPrivilegeDto;
import com.kiwihouse.dto.Role;
import com.kiwihouse.mapper.PrivilegeMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.AdminRoleAddVo;
import com.kiwihouse.vo.kiwihouse.RoleAddVo;
import com.kiwihouse.vo.kiwihouse.RoleUpdateVo;

/**
 * @author yjzn
 * @date 2019-12-24-下午 2:24
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PrivilegeService {

    @Autowired
    PrivilegeMapper privilegeMapper;
    @Autowired
    Properties properties;
    @Autowired
    CheckAdminService checkAdminService;

    String superRoleId = "1";   //一级管理员的角色ID为1
    String secondRoleId = "2";  //二级管理员的角色ID为2

    /**
     * isTopMg: is top manager
     * @param adminId 管理员ID
     * @return 是否为最高管理员
     */
    public boolean isTopMg(String adminId){

        if(StringUtils.isBlank(adminId)){
            return false;
        }
        String rank = privilegeMapper.isTopMg(adminId);
        return superRoleId.equals(rank);
    }

    /**
     * 一二级管理员可以创建管理员,并且只能创建级别小于等于自己级别的管理员
     * @param adminId 管理员ID
     * @return 是否可以创建管理员
     */
    public boolean isTopTwoMg(String adminId) {

        if(StringUtils.isBlank(adminId)){
            return false;
        }
        String rank = privilegeMapper.isTopMg(adminId);
        return superRoleId.equals(rank) || secondRoleId.equals(rank);
    }

    /**
     * 校验登录用户是否具有访问权限
     * @param adminId 请求头中拿到的管理员ID
     * @return true-用户具有访问权限，false-用户不具有访问权限
     */
    public boolean verify(String adminId,StringBuffer requestUrl,String requestMethod) {

        //解析出URL中的请求路径,以端口切分
        String[] split = requestUrl.toString().split(properties.getPort());
        String requestPath = split[1];

        ArrayList<PrivilegeDto> list = queryPrivilegeInfo(adminId);

        //admin_role为空 -> 查询数据为空 ->用户没有被赋予权限
        if(CollectionUtils.isEmpty(list)){
            return false;
        }else {
            for (PrivilegeDto privilegeDto : list) {
                String method = privilegeDto.getRequestMethod();
                String url = privilegeDto.getRequestUrl();
                if (requestMethod.equalsIgnoreCase(method) && requestPath.startsWith(url)) {
                    return true;
                }
            }
            return false;
        }

    }

    /**
     *
     * @return 所有权限信息集合
     */
    public ResultList queryInfo() {
        List<ProPrivilegeDto> list = privilegeMapper.queryInfo();
        if(CollectionUtils.isEmpty(list)){
            return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(),null);
        }else{
            Integer row = privilegeMapper.queryInfoRow();
            return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(),new Result<>(row,list));
        }
    }

    /**
     * 查询用户对应的权限信息
     * @param adminId 请求头中拿到的管理员ID
     * @return 权限信息对象集合
     */
    private ArrayList<PrivilegeDto> queryPrivilegeInfo(String adminId) {
        return privilegeMapper.verify(adminId);
    }

    /**
     * 查询角色所属权限信息
     * @param roleId 角色ID
     * @return 角色对应权限信息或当roleId为null时返回全部用户对应的权限信息
     */
    public ResultList queryPORInfo(String roleId,String roleName) {
        HashMap<String,String> map = new HashMap<>(1);
        map.put("roleId",roleId);
        map.put("roleName",roleName);
        ArrayList<PorRoleDto> list = privilegeMapper.queryPORInfo(map);
        Integer row = privilegeMapper.queryPORInfoRow(map);
        return ResultUtil.verifyQuery(list,row);
    }


    /**
     * 查询管理员对应的角色信息或权限信息
     * type=1：查询角色信息，type=2：查询权限信息
     * @param doAdminId 执行操作的管理员
     * @param adminId 被查询管理员ID
     * @param type 查询数据类型
     * @return 对应的角色或权限
     */
    public ResultList queryPROA(String doAdminId,String adminId,String type) {
        if(isTopMg(adminId)){
            if("1".equals(type)) {
                Role role = new Role().setRoleId(1).setRoleDesc("拥有所有权限功能").setRoleName("一级管理员");
                ArrayList<Object> list = new ArrayList<>();
                list.add(role);
                return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(1,list));
            }else if ("2".equals(type)) {
                return new ResultList(Code.PRIVILEGE_ALL.getCode(),Code.PRIVILEGE_ALL.getMsg(),null);
            }else{
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "type值不正确", null);
            }
        }else {

            if(checkAdminService.queryAdminIds(doAdminId, adminId)) {
                return doQueryPROA(doAdminId,adminId,type);
            }else{
                return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "只能查询下级管理员和个人信息", null);
            }
        }
    }

    /**
     * 执行查询操作
     * @param doAdminId 执行操作的管理
     * @param adminId 被查询的管理员ID
     * @param type 查询的数据类型
     * @return 管理员的角色或权限
     */
    private ResultList doQueryPROA(String doAdminId,String adminId,String type){
        if(StringUtils.isBlank(adminId)){
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"type不能为空",null);
        }

        HashMap<String,String> map = new HashMap<>();
        if(doAdminId.equals(adminId)){
            if("1".equals(type)) {
                Role role = new Role().setRoleId(2).setRoleDesc("拥有个人设备所有权限功能").setRoleName("二级管理员");
                ArrayList<Object> list = new ArrayList<>();
                list.add(role);
                return new ResultList(Code.QUERY_SUCCESS.getCode(),Code.QUERY_SUCCESS.getMsg(),new Result<>(1,list));
            }else if ("2".equals(type)) {
                return new ResultList(Code.PRIVILEGE_ALL.getCode(),Code.PRIVILEGE_ALL.getMsg(),null);
            }else{
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "type值不正确", null);
            }
        }else {

            //查询三级管理员信息
            if("1".equals(type)) {
                map.put("adminId", adminId);
                List<Role> list = privilegeMapper.queryROA(map);  //查询角色信息
                if(list.isEmpty()) {
                    return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(), null);
                }else {
                    Integer row = privilegeMapper.queryROARow(map);
                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
                }
            }else if ("2".equals(type)) {
                map.put("adminId", adminId);
                List<Privilege> list = privilegeMapper.queryPOA(map);  //查询权限信息
                if(list.isEmpty()) {
                    return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(), null);
                }else {
                    Integer row = privilegeMapper.queryPOARow(map);
                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
                }
            }else {
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), "type值不正确", null);
            }
        }


    }

    /**
     *
     * @param roleAddVo 角色添加信息
     * @return 是否创建成功
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultList addRole(RoleAddVo roleAddVo) {
        String currentTime = TimeUtil.getCurrentTime();
        roleAddVo.setAddTime(currentTime);
        Integer roleRow = privilegeMapper.addRole(roleAddVo);
        if(1==roleRow){

            ArrayList<HashMap<String,String>> list = new ArrayList<>();
            String roleId = roleAddVo.getRoleId();
            roleAddVo.getPrivilegeList().forEach(privilegeId -> {
                HashMap<String,String> map = new HashMap<>();
                map.put("roleId",roleId);
                map.put("privilegeId",privilegeId);
                map.put("addTime", currentTime);
                list.add(map);

            });
            return addRolePrivilege(list);

        }else{
            return new ResultList(Code.ADD_FAIL.getCode(),Code.ADD_FAIL.getMsg(),null);
        }
    }

    /**
     * 为角色赋权
     * @param list 权限ID集合
     * @return
     */
    private ResultList addRolePrivilege(ArrayList<HashMap<String,String>> list){
        return ResultUtil.verifyAdd(privilegeMapper.addRolePrivilege(list));
    }

    /**
     * 删除roleId的所有权限
     *  type=1：删除角色信息，type=2：删除对应的权限信息
     * @param type 删除数据类型
     * @param roleId 角色ID
     * @return
     */
    public ResultList deleteRolePrivilege(String type, String roleId){
        if(null==roleId || roleId.equals("")){
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"roleId不能为null或空字符串",null);
        }
        Integer row = 0;
        if("1".equals(type)){
            row = privilegeMapper.deleteRole(roleId);
        }else if ("2".equals(type)){
            row = privilegeMapper.deleteRolePrivilege(roleId);
        }else{
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"type值不在规定区间",null);
        }
        return ResultUtil.verifyDelete(row);
    }

    /**
     * 为三级管理员赋角色
     * @param adminRoleAddVo 参数信息
     * @return
     */
    public ResultList addAdminRole(AdminRoleAddVo adminRoleAddVo,String doAdminId) {
        if(isTopMg(doAdminId)){
            return doAddAdminRole(adminRoleAddVo);
        }else {
            if(checkAdminService.queryAdminIds(doAdminId, adminRoleAddVo.getAdminId())) {
                return doAddAdminRole(adminRoleAddVo);
            }else{
                return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "只能为下级管理员设置角色", null);
            }
        }
    }

    /**
     * 执行赋予角色操作
     * @param adminRoleAddVo
     * @return 是否操作成功
     */
    private ResultList doAddAdminRole(AdminRoleAddVo adminRoleAddVo){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();
        String adminId = adminRoleAddVo.getAdminId();
        String currentTime = TimeUtil.getCurrentTime();
        adminRoleAddVo.getRoleIdList().forEach(roleId -> {
            HashMap<String,String> map = new HashMap<>();
            map.put("adminId",adminId);
            map.put("roleId",roleId);
            map.put("addTime",currentTime);
            list.add(map);

        });
        return ResultUtil.verifyAdd(privilegeMapper.addAdminRole(list));
    }


    /**
     * 修改角色信息
     * @param roleUpdateVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultList updateRole(RoleUpdateVo roleUpdateVo) {
        Integer roleRow = privilegeMapper.updateRole(roleUpdateVo);
        if(null==roleRow || roleRow==0){
            return new ResultList(Code.UPDATE_NULL.getCode(),Code.UPDATE_NULL.getMsg(),null);
        }else{
            if(CollectionUtils.isNotEmpty(roleUpdateVo.getPrivilegeList())){
                String currentTime = TimeUtil.getCurrentTime();
                String type = "2";  //删除角色对应的全部权限
                ResultList resultList = deleteRolePrivilege(type, roleUpdateVo.getRoleId());
                if (Code.DELETE_SUCCESS.getCode() == resultList.getCode()) {
                    ArrayList<HashMap<String, String>> list = new ArrayList<>();
                    String roleId = roleUpdateVo.getRoleId();
                    roleUpdateVo.getPrivilegeList().forEach(privilegeId -> {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("roleId", roleId);
                        map.put("privilegeId", privilegeId);
                        map.put("addTime",currentTime);
                        list.add(map);
                    });
                    ResultList resultList1 = addRolePrivilege(list);
                    if (Code.ADD_SUCCESS.getCode() == resultList1.getCode()) {
                        return new ResultList(Code.UPDATE_SUCCESS.getCode(), Code.UPDATE_SUCCESS.getMsg(), null);
                    } else {
                        return resultList1;
                    }
                } else {
                    return resultList;
                }

            }else{
                return new ResultList(Code.UPDATE_SUCCESS.getCode(), Code.UPDATE_SUCCESS.getMsg(), null);
            }
        }

    }

    /**
     * 删除该管理员的角色
     * @param adminId 管理员ID
     * @return
     */
    public ResultList deleteAdminRole(String adminId) {
        if(StringUtils.isBlank(adminId)){
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"admin is not null",null);
        }
        return ResultUtil.verifyDelete(privilegeMapper.deleteAdminRole(adminId));
    }
}
