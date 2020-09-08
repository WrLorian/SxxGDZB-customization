package com.kiwihouse.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.SqlProvider.ThreePhaseMeasureProvider;
import com.kiwihouse.dto.ThreePhase.ThreePhasePowerDao;
import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

/**
 * @author xin
 * @desc 功率
 * @date 2020/6/4
 */
@Repository
public interface ThreePhasePowerMapper {

    /**
     * @param threePhaseVo
     * @return
     */
    @SelectProvider(type = ThreePhaseMeasureProvider.class, method = "getMaxPower")
    List<ThreePhasePowerDao> getMaxPower(ThreePhaseVo threePhaseVo);
}
