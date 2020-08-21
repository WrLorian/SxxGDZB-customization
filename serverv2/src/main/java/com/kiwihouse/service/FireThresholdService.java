package com.kiwihouse.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.utils.TimeUtil;
import com.kiwihouse.mapper.FireThresholdMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.ThresholdUpdateVo;

/**
 * @author yjzn
 * @date 2020-03-17-下午 5:39
 */
@Service
public class FireThresholdService {

    @Autowired
    FireThresholdMapper fireThresholdMapper;

    public ResultList updateInfo(ThresholdUpdateVo updateVo) {

        ThresholdUpdateVo thresholdUpdateVo = fireThresholdMapper.queryInfo(updateVo.getEqptSn());
        if(null==thresholdUpdateVo){
            updateVo.setAddTime(TimeUtil.getCurrentTime());
            Integer row = fireThresholdMapper.addInfo(updateVo);
            return ResultUtil.verifyAdd(row);
        }else {
            updateVo.setUpdateTime(TimeUtil.getCurrentTime());
            Integer row = fireThresholdMapper.updateInfo(updateVo);
            return ResultUtil.verifyUpdate(row);
        }
    }

    /**
     * 查询预警阀值
     * @param eqptSn
     * @return
     */
    public ResultList queryInfo(String eqptSn,String adminId,boolean isTopMg) {
        HashMap<String,Object> map = new HashMap<>();
        map.put("eqptSn",eqptSn);
        if(isTopMg) {
            map.put("adminId",null);
        }else{
            map.put("adminId",adminId);
        }
        return ResultUtil.verifyQuery(fireThresholdMapper.query(map),fireThresholdMapper.queryRow(map));
    }
}
