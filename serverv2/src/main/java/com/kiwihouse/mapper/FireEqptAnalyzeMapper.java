package com.kiwihouse.mapper;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.AlmAndEqptNumDto;
import com.kiwihouse.dto.FireEqptAnalyzeDto;
import com.kiwihouse.vo.kiwihouse.DataAnalyzeVo;

/**
 * @author yjzn
 * @date 2020-02-28-下午 3:37
 */
@Repository
public interface FireEqptAnalyzeMapper {

    List<FireEqptAnalyzeDto> queryInfo(DataAnalyzeVo dataAnalyzeVo);

    List<FireEqptAnalyzeDto> queryAll(DataAnalyzeVo dataAnalyzeVo);

    //根据Code分析数据
    AlmAndEqptNumDto queryAlmNums(HashMap<String,Object> map);

    Integer queryEqptNums(HashMap<String,Object> map);

    Integer queryOrderNum(HashMap<String, Object> map);
}
