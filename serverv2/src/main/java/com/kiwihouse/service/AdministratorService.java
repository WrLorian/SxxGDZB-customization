package com.kiwihouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dao.mapper.GroupMapper;
import com.kiwihouse.dto.AdminQueryDto;
import com.kiwihouse.mapper.AdministratorMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.AdminAddVo;
import com.kiwihouse.vo.kiwihouse.AdminQueryVo;
import com.kiwihouse.vo.kiwihouse.AdminUpdateVo;

/**
 * @author yjzn
 * @date
 */
@Service
public class AdministratorService {

    @Autowired
    AdministratorMapper administratorMapper;
    @Autowired
    CheckAdminService checkAdminService;
    @Autowired
    PrivilegeService privilegeService;
    @Autowired
    GroupMapper groupMapper;


    private final String FIRST_RANK = "1";

    /**
     * 管理员信息录入
     *
     * @param adminAddVo 录入信息参数
     * @param request    HTTP Request
     * @return 是否录入成功
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultList addInfo(AdminAddVo adminAddVo, HttpServletRequest request) {
        String rank = adminAddVo.getRank();
        String doAdminId = request.getHeader("dz-usr");
        boolean isTopMg = privilegeService.isTopMg(doAdminId);

        if (FIRST_RANK.equals(rank)) {
            return ResultUtil.resp(Code.PRIVILEGE_FAIL);
        }
        //判断只有一级管理员可以创建一级管理员账号
//        if(FIRST_RANK.equals(rank)){
//            if(!isTopMg){
//                return new ResultList(Code.PRIVILEGE_FAIL.getCode(),"没有添加管理员权限",null);
//            }
//        }

        adminAddVo.setAddTime(TimeUtil.getCurrentTime());
        adminAddVo.setParentId(doAdminId);
        Integer row = administratorMapper.addInfo(adminAddVo);
        if (row == 0) {
            return ResultUtil.resp(Code.ADD_FAIL);
        } else {
            String adminId = adminAddVo.getAdminId();   //被添加的管理员
            administratorMapper.addParent(adminId, doAdminId);   //添加管理员对应的上级管理员
//            List<Integer> groupIds = adminAddVo.getGroupIds();
//            if (!groupIds.isEmpty()) {
//                List<HashMap<String, Object>> maps = handleList(groupIds, adminId, doAdminId, isTopMg);
//                groupMapper.updateGroupIds(maps);
//                //给管理员添加分组需要将设备对应的管理员改为该管理员
//                groupIds.forEach(groupId -> {
//                    administratorMapper.updateEqptAdmin(groupId, adminId);
//                });
//            }
            return ResultUtil.respPage(Code.ADD_SUCCESS, 1, adminId);
        }
    }

    /**
     * @param groupIds
     * @param adminId
     * @param doAdminId
     * @param isTopMg
     * @return
     */
    private List<HashMap<String, Object>> handleList(List<Integer> groupIds, String adminId, String doAdminId, boolean isTopMg) {
        List<HashMap<String, Object>> updateList = new ArrayList<>();
        if (!groupIds.isEmpty()) {
            groupIds.forEach(groupId -> {
                if (groupId <= 0) {
                    throw new RuntimeException("更新分组所属管理员失败-管理员ID不能小于0");
                }
                HashMap<String, Object> map = new HashMap<>();
                map.put("groupId", groupId);
                map.put("adminId", adminId);
                if (isTopMg) {
                    map.put("doAdminId", null);
                } else {
                    map.put("doAdminId", doAdminId);
                }
                updateList.add(map);
            });
        }
        return updateList;
    }

    /**
     * 查询管理员信息：
     * type=1 查询个人信息
     * type=2
     * 当前管理员为超级管理员时,查询全部信息
     * 当前管理员为二级管理员时,查询下属管理员信息
     *
     * @param adminQueryVo 查询参数
     * @param adminId      管理员ID
     * @return 管理员信息
     */
    public ResultList queryInfo(AdminQueryVo adminQueryVo, String adminId, boolean isTopMg) {

        String type = adminQueryVo.getType();

        //查询个人信息
        if ("1".equals(type)) {
            adminQueryVo.setAdminId(adminId);
            adminQueryVo.setParentId(null);
            List<AdminQueryDto> list = administratorMapper.queryFullInfo(adminQueryVo);
            if (list.isEmpty()) {
                return ResultUtil.resp(Code.QUERY_NULL);
            } else {
                list.forEach(adminQueryDto -> {
                    adminQueryDto.setParentAdminName(administratorMapper.queryName(adminQueryDto.getParentId()));
                });
                return ResultUtil.respPage(Code.QUERY_SUCCESS, list.size(), list);
            }
        } else if ("2".equals(type)) {     //查询下属管理员信息
            adminQueryVo.setAdminId(null);
            if (isTopMg) {    //如果是超级管理员则查询全部信息,如果是二级管理员则查询下属管理员信息
                adminQueryVo.setParentId(null);
            } else {
                adminQueryVo.setParentId(adminId);
            }
            PageHelper.startPage(adminQueryVo.getPage(),adminQueryVo.getLimit());
            List<AdminQueryDto> list = administratorMapper.queryInfo(adminQueryVo);
            if (list.isEmpty()) {
                return ResultUtil.resp(Code.QUERY_NULL);
            } else {
                //手动处理分页

//                HashMap<Integer, List> map = GroupList.groupList(list, adminQueryVo.getLimit());
//                List<AdminQueryDto> groupList = map.get(adminQueryVo.getPage() - 1);
//                groupList.forEach(adminQueryDto -> {
//                    adminQueryDto.setParentAdminName(administratorMapper.queryParentName(adminQueryDto.getAdminId()));
//                });
                list.forEach(adminQueryDto -> {
                    adminQueryDto.setParentAdminName(administratorMapper.queryName(adminQueryDto.getParentId()));
                });
                return ResultUtil.respPage(Code.QUERY_SUCCESS,list);
            }
        } else {
            return ResultUtil.resp(Code.QUERY_FAIL, "type值不正确");
        }
    }


    /**
     * 各级管理员用户都可以更新个人信息
     * 一级用户可以修改二级和三级用户信息
     * 二级用户可以修改三级用户信息
     * 三级用户只能修改个人信息
     * <p>
     * 注：
     * 每个用户对应的上级管理员用户只能由超级管理员修改
     *
     * @param adminUpdateVo 更新参数
     * @param doAdminId     执行删除的管理员ID
     * @param isTopMg       是否为一级管理员
     * @return 管理员信息
     */
    public ResultList updateInfo(AdminUpdateVo adminUpdateVo, String doAdminId, boolean isTopMg) {

        //被修改信息的管理员ID
        String adminId = adminUpdateVo.getAdminId();

        Integer parentId = adminUpdateVo.getParentId();
        boolean isParent  = doAdminId.equals(parentId.toString());
        boolean isMyself    = doAdminId.equals(adminId);
        //当parentId不为空时表示要修改上级管理员，则判断操作的管理员是否为一级管理员
        if (parentId > 0 && !isTopMg && !isParent) {
            return ResultUtil.resp(Code.PRIVILEGE_FAIL, "只有一级管理员才可以修改管理员对应的上级管理员");
        }

        //只有一级管理员可以修改管理员级别
        String rank = adminUpdateVo.getRank();
        if (StringUtils.isNotBlank(rank) && !isTopMg &&!isParent ) {
             return ResultUtil.resp(Code.PRIVILEGE_FAIL, "只有一级管理员可以修改管理员级别");
        }


//        List<Integer> groupIds = adminUpdateVo.getGroupIds();
//        if (null != groupIds && !groupIds.isEmpty()) {
//            List<HashMap<String, Object>> maps = handleList(groupIds, adminUpdateVo.getAdminId(), doAdminId, isTopMg);
//            groupMapper.updateGroupIds(maps);
//            groupIds.forEach(groupId -> {
//                administratorMapper.updateEqptAdmin(groupId, adminId);
//            });
//
//        }

        if (isTopMg || isMyself || isParent) {
            /*
                一级管理员直接执行修改操作
                修改人和被修改人相同，既修改个人信息时直接执行
             */
            return doUpdate(adminUpdateVo);
        } else {
            return ResultUtil.resp(Code.PRIVILEGE_FAIL, "只能修改下级管理员信息");
        }
    }

    /**
     * 执行更新操作
     *
     * @param adminUpdateVo 更新管理员参数
     * @return 更新结果
     */
    private ResultList doUpdate(AdminUpdateVo adminUpdateVo) {
        if (null == adminUpdateVo) {
            return new ResultList(Code.UPDATE_FAIL.getCode(), "更新参数为null", null);
        }
        return ResultUtil.verifyUpdate(administratorMapper.updateInfo(adminUpdateVo));
    }

    /**
     * 一级管理员可以对所有管理员执行操作
     * 二级管理员可以删除下属管理员
     * 三级管理员没有删除权限
     *
     * @param adminId   被删除管理员
     * @param topMg     是否为一级管理员
     * @param doAdminId 执行删除管理员
     * @return
     */
    public ResultList deleteInfo(String adminId, boolean topMg, String doAdminId) {
        if (topMg) {
            return doDelete(adminId);
        } else {
            if (!checkAdminService.queryAdminIds(doAdminId, adminId)) {
                return new ResultList(Code.PRIVILEGE_FAIL.getCode(), "只能删除下级管理员信息", null);
            }
            return doDelete(adminId);
        }
    }

    /**
     * 执行删除操作
     *
     * @param adminId 要删除的管理员ID
     * @return 是否删除成功
     */
    private ResultList doDelete(String adminId) {
        if (null == adminId) {
            return new ResultList(Code.UPDATE_FAIL.getCode(), "更新参数为null", null);
        }
        return ResultUtil.verifyDelete(administratorMapper.deleteInfo(adminId));
    }
}
