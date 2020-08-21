package com.kiwihouse.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.ThresholdDto;
import com.kiwihouse.vo.kiwihouse.ThresholdUpdateVo;

/**
 * @author yjzn
 * @date 2020-03-17-下午 5:42
 */
@Repository
public interface FireThresholdMapper {

    Integer updateInfo(ThresholdUpdateVo updateVo);
    Integer addInfo(ThresholdUpdateVo updateVo);

    @Select("select * from fire_threshold where eqpt_sn = #{eqptSn}")
    ThresholdUpdateVo queryInfo(String eqptSn);

    List<ThresholdDto> query(HashMap<String, Object> map);
    Integer queryRow(HashMap<String, Object> map);
}
