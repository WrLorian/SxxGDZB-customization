package com.kiwihouse.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.SqlProvider.ThreePhaseMeasureProvider;
import com.kiwihouse.dto.ThreePhase.ThreePhaseMeasureDto;
import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

/**
 * @author xin
 * @desc 设备参数
 * @date 2020-04-30 13:38:22
 */
@Repository
public interface ThreePhaseMeasureMapper {


    @SelectProvider(type = ThreePhaseMeasureProvider.class, method = "getList")
    List<ThreePhaseMeasureDto> getList(ThreePhaseVo threePhaseVo);

    @SelectProvider(type = ThreePhaseMeasureProvider.class, method = "getListCount")
    Integer getListCount(ThreePhaseVo threePhaseVo);


    @Select("SELECT * FROM tp_measure WHERE imei=#{imei} ORDER by tp_measure_id DESC LIMIT 1")
    ThreePhaseMeasureDto getLastStatus(String imei);

}
