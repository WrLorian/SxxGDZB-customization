package com.kiwihouse.service;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.utils.CodeTransferUtil;
import com.kiwihouse.dto.SiteDto;
import com.kiwihouse.mapper.SiteMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.SiteAddVo;
import com.kiwihouse.vo.kiwihouse.SiteQueryVo;

/**
 * @author yjzn
 * @date
 */
@Service
public class SiteService {

    @Autowired
    SiteMapper siteMapper;
    @Autowired
    PrivilegeService privilegeService;

    /**
     * 查询地址信息
     * @param siteQueryVo 查询参数
     * @return 地址列表
     */
    public ResultList queryInfo(SiteQueryVo siteQueryVo) {
        CodeTransferUtil.transferAll(siteQueryVo.getCode(),siteQueryVo);
        List<SiteDto> list = siteMapper.queryInfo(siteQueryVo);
        Integer row = siteMapper.queryInfoRow(siteQueryVo);
        return ResultUtil.verifyQuery(list,row);
    }

    /**
     * 区域信息录入
     * @param siteAddVo
     * @return
     */
    public ResultList addInfo(SiteAddVo siteAddVo) {

        CodeTransferUtil.transferAll(siteAddVo.getCode(),siteAddVo);

        //判断area是否已存在
        List<String> list = siteMapper.queryArea(siteAddVo.getAdminId());
        String province = siteAddVo.getProvince();
        String city = siteAddVo.getCity();
        String district = siteAddVo.getDistrict();
        String address = siteAddVo.getArea();

        String voArea = ""
                .concat(StringUtils.isBlank(province) ? "" : province)
                .concat(StringUtils.isBlank(city) ? "" : city)
                .concat(StringUtils.isBlank(district) ? "" : district)
                .concat(address);

        for (String area : list) {
            if(voArea.equals(area)){
                return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),voArea+"已存在",null);
            }
        }

        return ResultUtil.verifyAdd(siteMapper.addInfo(siteAddVo));

    }

    /**
     * 删除区域信息
     *  1.该区域没有对应设备
     *  2.只能删除操作管理员所属的区域
     * @param siteId 删除地址ID
     * @return
     */
    public ResultList deleteInfo(String siteId,String doAdminId) {

        HashMap<String,String> map = new HashMap<>();
        map.put("siteId",siteId);
        if(privilegeService.isTopMg(doAdminId)){
            map.put("adminId",null);
        }else{
            map.put("adminId",doAdminId);
        }

        List<String> eqptSns = siteMapper.IsExistEqpt(siteId);
        if(eqptSns.isEmpty()){
            return doDelete(map);
        }else{
            return new ResultList(Code.DELETE_UNSAFE.getCode(),"该地区存在设备",null);
        }

    }

    /**
     * 执行删除操作
     * @param map 删除参数
     * @return
     */
    private ResultList doDelete(HashMap<String,String> map){
        return ResultUtil.verifyDelete(siteMapper.deleteInfo(map));
    }
}
