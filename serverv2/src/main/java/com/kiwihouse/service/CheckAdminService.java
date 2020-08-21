package com.kiwihouse.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Basic;
import com.kiwihouse.dao.mapper.AuthUserMapper;
import com.kiwihouse.mapper.AdministratorMapper;
import com.kiwihouse.mapper.EquipmentMapper;
import com.kiwihouse.mapper.KwhMgMapper;
import com.kiwihouse.mapper.MaintainMapper;

/**
 * @author yjzn
 * @date 2020-1-19 15:21:18
 */
@Service
public class CheckAdminService {

    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    KwhMgMapper kwhMgMapper;
    @Autowired
    EquipmentMapper equipmentMapper;
    @Autowired
    MaintainMapper maintainMapper;
    @Autowired
    AuthUserMapper userMapper;
    @Autowired
    AdministratorMapper administratorMapper;

    /**
     * 根据adminId对clazz对应中的adminId属性赋值
     * @param adminId 管理员ID
     * @param basic 继承Basic的目标对象
     */
    public void verifyAdminId(String adminId, Basic basic){
        if(privilegeService.isTopMg(adminId)){
            basic.setAdminId(null);
        }else{
            basic.setAdminId(adminId);
        }
    }

    /**
     * 判断groupId是否属于adminId所拥有的组
     * @param groupId 组Id
     * @param adminId 管理员Id
     * @return is siteId belong to admin?
     */
    public boolean isGroupIdBelong2Admin(String groupId,String adminId){
        if(privilegeService.isTopMg(adminId)){
            return true;
        }else{
            List<String> list = kwhMgMapper.queryGroups(adminId);
            return list.contains(groupId);
        }
    }

    /**
     * 更新设备-判断设备是否属于管理员用户
     * @param eqptId 设备ID
     * @param adminId 管理员ID
     * @return eqptId is belong to admin?
     */
    public boolean isEqptBelong2admin(String eqptId,String adminId){
        if(privilegeService.isTopMg(adminId)){
            return true;
        }else{
            List<String> list = equipmentMapper.queryEqptIds(adminId);
            return list.contains(eqptId);
        }
    }

    /**
     * 删除设备-判断设备是否属于管理员用户
     * @param eqptSn 设备序列号
     * @param adminId 管理员Id
     * @return is eqptSn belong to admin?
     */
    public boolean isEqptSnBelong2admin(String eqptSn, String adminId) {
        if(privilegeService.isTopMg(adminId)){
            return true;
        }else{
            List<String> list = equipmentMapper.queryEqptSns(adminId);
            return list.contains(eqptSn);
        }
    }

    /**
     * 修改维修信息-判断设备是否属于管理员用户
     * @param alarmId
     * @param adminId
     * @return is alarmId belong to admin?
     */
    public boolean isMtIdBelong2Admin(String alarmId, String adminId) {
        if(privilegeService.isTopMg(adminId)){
            return true;
        }else{
            List<String> list = maintainMapper.queryMtId(adminId);
            return list.contains(alarmId);
        }
    }

    /**
     * 修改用户-判断该用户是否属于管理员用户
     * @param userId 用户ID
     * @param adminId 管理员Id
     * @return is userId belong to admin?
     */
    public boolean isUserIdBelong2Admin(String userId, String adminId) {
        if(privilegeService.isTopMg(adminId)){
            return true;
        }else{
            List<String> list = userMapper.queryUserIds(adminId);
            return list.contains(userId);
        }

    }

    /**
     * 判断被修改信息的管理员是否为执行修改的管理员本身或其下级
     * @param doAdminId 执行修改的管理员ID
     * @param adminId 被修改信息的管理员ID
     * @return 被修改信息的管理员是否为执行修改的管理员本身或其下级
     */
    public boolean queryAdminIds(String doAdminId,String adminId){
        if(StringUtils.isBlank(doAdminId)){
            return false;
        }
        if(privilegeService.isTopMg(doAdminId)){
            return true;
        }
        //查询执行修改信息的管理员的下级管理员ID及其自身ID
        List<String> list = administratorMapper.queryAdminIds(doAdminId);
        return list.contains(adminId);
    }
}
