package com.kiwihouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.bean.DataType;
import com.kiwihouse.common.utils.CodeTransferUtil;
import com.kiwihouse.dto.SmokeDBInfo;
import com.kiwihouse.dto.SmokeMsg;
import com.kiwihouse.mapper.SmokeDevReportInfoMapper;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.SmokeReportedQueryVo;

/**
 * @author yjzn
 * @date
 */
@Service
public class SmokeDevReportInfoService {

    @Autowired
    SmokeDevReportInfoMapper smokeDevReportInfoMapper;

    /**
     * 查询烟感设备上报信息
     * @param smokeReportedQueryVo 查询参数
     * @return 三类上报信息
     */
    public ResultList queryInfo(SmokeReportedQueryVo smokeReportedQueryVo) {

        CodeTransferUtil.transferOne(smokeReportedQueryVo.getCode(),smokeReportedQueryVo);

        if(DataType.SMOKE_REPORT_SC.equals(smokeReportedQueryVo.getType())){
            smokeReportedQueryVo.setOrderBy("add_time");
        }else {
            String orderBy = smokeReportedQueryVo.getOrderBy();
            if ("addTime".equals(orderBy)) {
                smokeReportedQueryVo.setOrderBy("add_time");
            }else{

                if(!"smoke".equals(orderBy)&&!"temperature".equals(orderBy) &&!"signal".equals(orderBy) &&!"humidity".equals(orderBy) &&!"battery".equals(orderBy)){
                    return new ResultList(Code.PARAM_FORMAT_ERROR.getCode(),"orderBy参数不正确",null);
                }
            }
        }

        String sequence = smokeReportedQueryVo.getSequence();
        if("ascending".equals(sequence)){
            smokeReportedQueryVo.setSequence("asc");
        }else{
            smokeReportedQueryVo.setSequence("desc");
        }
        List<SmokeDBInfo> list = smokeDevReportInfoMapper.queryInfo(smokeReportedQueryVo);
        if(list.isEmpty()){
            return new ResultList(Code.QUERY_NULL.getCode(),Code.QUERY_NULL.getMsg(),null);
        }else{
            Integer row = smokeDevReportInfoMapper.queryInfoRow(smokeReportedQueryVo);
            if(row==0){
                return new ResultList(Code.QUERY_FAIL.getCode(),Code.QUERY_FAIL.getMsg(),null);
            }else{
                String type = smokeReportedQueryVo.getType();
                if(DataType.SMOKE_REPORT_SC.equals(type)){  //自检信息
                    ArrayList<SmokeMsg> arrayList = new ArrayList<>();
                    list.forEach(smokeDB -> {
                        String msg = smokeDB.getSmokeMsg();
                        SmokeMsg smokeMsg = JSONObject.parseObject(msg, SmokeMsg.class);
                        smokeMsg.setSmokeId(smokeDB.getSmokeId())
                                .setEqptSn(smokeDB.getEqptSn())
                                .setStatus(smokeDB.getStatus())
                                .setType(smokeDB.getType())
                                .setAddTime(smokeDB.getAddTime())
                                .setBattery(smokeDB.getBattery())
                                .setSignal(smokeDB.getSignal())
                                .setEqptName(smokeDB.getEqptName())
                                .setEqptType(smokeDB.getEqptType())
                                .setUserId(smokeDB.getUserId())
                                .setUserName(smokeDB.getUserName())
                                .setPhone(smokeDB.getPhone())
                                .setProvince(smokeDB.getProvince())
                                .setCity(smokeDB.getCity())
                                .setDistrict(smokeDB.getDistrict())
                                .setAddress(smokeDB.getAddress())
                                .setEqptAddr(smokeDB.getEqptAddr());

                        arrayList.add(smokeMsg);
                    });
                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, arrayList));
                }else {     //普通信息和紧急信息上报数据格式相同，区别只是数值不同
                    list.forEach(smokeDB -> {
                        smokeDB.setSmokeMsg(null);
                        smokeDB.setDescribeMsg(null);
                    });
                    return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(row, list));
                }
            }
        }

    }
}
