package com.kiwihouse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.EqptTypeSta;
import com.kiwihouse.common.bean.UserInfo;
import com.kiwihouse.common.customException.ParamException;
import com.kiwihouse.common.utils.CodeTransferUtil;
import com.kiwihouse.common.utils.GroupList;
import com.kiwihouse.common.utils.RedisUtil;
import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.domain.vo.ResultInfo;
import com.kiwihouse.dto.Eqpt4UpdateDto;
import com.kiwihouse.dto.EqptInfoDto;
import com.kiwihouse.dto.SiteDto;
import com.kiwihouse.dto.equipt.Equipt;
import com.kiwihouse.mapper.EquipmentMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.EqptAddVo;
import com.kiwihouse.vo.kiwihouse.EqptQueryVo;

/**
 * @author yjzn
 * @date 2019-12-30-上午 10:28
 */
@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class EquipmentService {
    private final Logger logger = LoggerFactory.getLogger(EquipmentService.class);

    @Autowired
    EquipmentMapper equipmentMapper;

    @Autowired
    CommandIssueService commandIssueService;
    @Autowired
    RedisUtil redisUtil;

    /**
     * 查询设备信息分两种情况(查询设备需要调用onenet平台的接口)
     * 1.查询设备状态
     * 2.不查询设备状态
     *
     * @param eqptQueryVo 查询参数对象
     * @return 设备信息
     */
    public Map<String, Object> queryInfo(EqptQueryVo eqptQueryVo, AuthUser authUser) {
            Map<String, Object> map = new HashMap<String, Object>();
            if (authUser.getIsAdmin()>0 ) {//不是管理员
                PageHelper.startPage(eqptQueryVo.getPage(), eqptQueryVo.getLimit());
                List<EqptInfoDto> list = equipmentMapper.querInfoByUserId(authUser.getUid().toString());
                if (list.isEmpty()) {
                	map.put("data",null);
                	map.put("msg", Code.QUERY_NULL.getMsg());
                	map.put("count", list.size());
                	map.put("code", 0);
                    return map;
                }
                map.put("data", list);
            	map.put("msg", Code.QUERY_SUCCESS.getMsg());
            	map.put("count", list.size());
            	map.put("code", 0);
                return map;
            }

            CodeTransferUtil.transferOne(eqptQueryVo.getCode(), eqptQueryVo);

            String online = eqptQueryVo.getOnline();
//            if (StringUtils.isBlank( eqptQueryVo.getAdminId() ) && userInfo.getGroupIds().length > 0) {
//                eqptQueryVo.setGroupIds(userInfo.getGroupIds());
//            }
            if (StringUtils.isNotBlank(online)) {    //查询设备状态
                ArrayList<EqptInfoDto> list = equipmentMapper.queryInfo(eqptQueryVo);
                if (list.isEmpty()) {
                	map.put("data",null);
                	map.put("msg", Code.QUERY_NULL.getMsg());
                	map.put("count", list.size());
                	map.put("code", 0);
                    return map;
                } else {
                    list.forEach(eqptInfoDto -> {
                        String deviceId = eqptInfoDto.getDeviceId();
                        String eqptType = eqptInfoDto.getEqptType();
                        String eqptSn = eqptInfoDto.getEqptSn();
                        eqptInfoDto.setEqptStatus(String.valueOf(Code.NOTONLINE.getCode()));
                        if (redisUtil.hasKey(eqptInfoDto.getImei())) {
                            eqptInfoDto.setEqptStatus(String.valueOf(Code.ONLINE.getCode()));
                        }
                    });

                    List<EqptInfoDto> collect;
                    if ("1".equals(online)) {
                        collect = list.stream().filter(eqptInfoDto -> eqptInfoDto.getEqptStatus().equals(String.valueOf(Code.ONLINE.getCode()))).collect(Collectors.toList());
                    } else if ("-1".equals(online)) {
                        collect = list.stream().filter(eqptInfoDto -> eqptInfoDto.getEqptStatus().equals(String.valueOf(Code.NOTONLINE.getCode()))).collect(Collectors.toList());
                    } else {
                        collect = list;
                    }
                    //手动处理分页
                    HashMap<Integer, List> maps = GroupList.groupList(collect, eqptQueryVo.getLimit());
                    map.put("data", map.get(eqptQueryVo.getPage() - 1));
                	map.put("msg", Code.QUERY_SUCCESS.getMsg());
                	map.put("count", collect.size());
                	map.put("code", 0);
                    return map;
                }
            } else {  //不查询设备状态
                Integer count = equipmentMapper.queryInfoCount(eqptQueryVo);
                eqptQueryVo.setPage(eqptQueryVo.getPage() - 1);
                List<EqptInfoDto> list =  equipmentMapper.querInfoByUserIdPage(eqptQueryVo);
                if (list.isEmpty()) {
                	map.put("data",null);
                	map.put("msg", Code.QUERY_NULL.getMsg());
                	map.put("count", list.size());
                	map.put("code", 0);
                    return map;
                } else {
                    list.forEach(eqptInfoDto -> {
                        eqptInfoDto.setEqptStatus(String.valueOf(Code.NOTONLINE.getCode()));
                        if (redisUtil.hasKey(eqptInfoDto.getImei())) {
                            eqptInfoDto.setEqptStatus(String.valueOf(Code.ONLINE.getCode()));
                        }
                    });
                    map.put("data", list);
                	map.put("msg", Code.QUERY_SUCCESS.getMsg());
                	map.put("count", count);
                	map.put("code", 0);
                    return map;
                }
            }
    }

    /**
     * 更新设备信息
     *
     * @param updateDto 更新设备信息参数对象
     * @return 是否更新成功
     */
    public ResultList updateInfo(Eqpt4UpdateDto updateDto) {
        //1.如果code存在，判断该区域是否已存在，存在则将设备siteId改为该siteId，不存在则录入地址然后将设备siteId改为该新录入的siteId
        Eqpt4UpdateDto eqpt4UpdateDto = addSite(updateDto);
        return ResultUtil.verifyUpdate(equipmentMapper.updateInfo(eqpt4UpdateDto));
    }

    /**
     * 录入设备信息(应该是设备生产完成，入库的时候使用)
     *
     * @param eqptAddVo 录入设备信息参数
     * @return 是否录入成功
     */
    @Transactional(rollbackFor = Exception.class)
    public ResultList addInfo(EqptAddVo eqptAddVo, UserInfo userInfo) {
        String userId = userInfo.getUserId();
        String voltage = eqptAddVo.getVoltage();

        boolean isAdminUser = userInfo.isAdmin();
        EqptAddVo eqptAddVo1 = eqptAddVo;
        if (isAdminUser) {
            eqptAddVo1 = addSite(eqptAddVo);
        } else {
            eqptAddVo1.setUserId(userId);
            eqptAddVo1.setDoAdminId("1");
            eqptAddVo1.setGroupId("1");
        }

        eqptAddVo1.setAddTime(TimeUtil.getCurrentTime());

        if (voltage != null && voltage.equals("220")) {
            eqptAddVo1.setEqptType(EqptTypeSta.DX);
        } else if (voltage != null && voltage.equals("380")) {
            eqptAddVo1.setEqptType(EqptTypeSta.SX);
        }
        EqptInfoDto info = equipmentMapper.queryInfoBySnAndImei(eqptAddVo.getEqptSn(), eqptAddVo.getImei());

        if (info != null) {
            if (!StringUtils.isBlank(info.getUserId()) && info.getUserId().equals(userId)) {
                return ResultUtil.resp(Code.ADD_SUCCESS);
            }
            if (equipmentMapper.updateUserId(info.getImei(), info.getEqptSn(), userId) > 0) {
                return ResultUtil.resp(Code.ADD_SUCCESS);
            }
            return ResultUtil.resp(Code.ADD_FAIL);
        }
        Integer row = equipmentMapper.addInfo(eqptAddVo1);
        if (row == 0) {
            return new ResultList(Code.ADD_FAIL.getCode(), Code.ADD_FAIL.getMsg(), null);
        } else {

            String string = "";
//            String register = eqptAddVo.getRegister();
//            if ("1".equals(register)) {  //同时注册到onenet平台
//                OneNetResult oneNetResult = commandIssueService.registerToOnenet(eqptAddVo.getEqptId(), eqptAddVo.getImei(), eqptAddVo.getImsi(), eqptAddVo.getEqptSn(), eqptAddVo.getEqptType(), eqptAddVo.getDoAdminId());
//                String errno = oneNetResult.getErrno();
//                if (OneNtCode.SUCC.equals(errno)) {
//                    string = "-注册onenet成功";
//                } else if (OneNtCode.INVALID_AUTH_INFO.equals(errno)) {
//                    throw new RuntimeException("注册到onenet平台失败>> IMEI或IMSI不正确(大多数情况为IMEI错误)");
//                }
//            }
            return new ResultList(Code.ADD_SUCCESS.getCode(), Code.ADD_SUCCESS.getMsg() + string, null);
        }
    }


    private EqptAddVo addSite(EqptAddVo eqptAddVo) {
        if (StringUtils.isBlank(eqptAddVo.getSiteId())) {
            if (StringUtils.isBlank(eqptAddVo.getCode()) || StringUtils.isBlank(eqptAddVo.getAddress())) {
                throw new ParamException(Code.PARAM_FORMAT_ERROR, "使用地址簿地址。或者选择录入新的地址");
            } else {
                CodeTransferUtil.transferAll(eqptAddVo.getCode(), eqptAddVo);
                SiteDto siteDto = equipmentMapper.querySiteInfo(eqptAddVo);
                if (null == siteDto) {
                    equipmentMapper.addSite(eqptAddVo);
                } else {
                    eqptAddVo.setSiteId(siteDto.getSiteId());
                }
            }
        }
        return eqptAddVo;
    }

    private Eqpt4UpdateDto addSite(Eqpt4UpdateDto updateDto) {
        //code不为空添加设备对应的地址，返回siteId
        if (!StringUtils.isBlank(updateDto.getCode())) {
            if (StringUtils.isBlank(updateDto.getAdminId()) || StringUtils.isBlank(updateDto.getAddress())) {
                throw new ParamException(Code.PARAM_FORMAT_ERROR.getCode(), "修改设备地址时必须同时传递详细地址以及adminId");
            }
            CodeTransferUtil.transferAll(updateDto.getCode(), updateDto);

            SiteDto siteDto = equipmentMapper.querySiteInfoForUpdate(updateDto);
            if (null == siteDto) {
                equipmentMapper.addSiteForUpdate(updateDto);
            } else {
                updateDto.setSiteId(siteDto.getSiteId());
            }
        }
        return updateDto;
    }

    /**
     * 删除设备信息
     *
     * @param eqptSn 设备序列号
     * @return 是否删除成功
     */
    public ResultList deleteInfo(String eqptSn, UserInfo userInfo) {
        if (!userInfo.isAdmin()) {
            if (equipmentMapper.deleteUserId(eqptSn) > 0) {
                return ResultUtil.resp(Code.UPDATE_SUCCESS);
            }
            return ResultUtil.resp(Code.UPDATE_FAIL);
        }
        if (StringUtils.isBlank(eqptSn)) {
            return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(), Code.PARAM_FORMAT_ERROR.getMsg(), null);
        }
        return ResultUtil.verifyDelete(equipmentMapper.deleteInfo(eqptSn));

    }

}
