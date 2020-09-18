package com.kiwihouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.MtInfoDto;
import com.kiwihouse.dto.MtSmokeInfoDto;
import com.kiwihouse.vo.kiwihouse.MtInfoVo;
import com.kiwihouse.vo.kiwihouse.MtUpdateVo;

/**
 * @author yjzn
 * @date 2020-1-4 10:57:47
 */
@Repository
public interface MaintainMapper {
    //query
    List<MtInfoDto> queryInfo(MtInfoVo mtInfoVo);
    Integer queryInfoRow(MtInfoVo mtInfoVo);
    List<MtSmokeInfoDto> querySmokeInfo(MtInfoVo mtInfoVo);
    Integer querySmokeInfoRow(MtInfoVo mtInfoVo);
    List<String> queryMtId(String adminId);

    //add
    Integer addInfo(@Param("alarmId") String alarmId,
                    @Param("eqptSn") String eqptSn,
                    @Param("addTime") String addTime);
    Integer addSmokeInfo(@Param("alarmId") String alarmId,
                         @Param("eqptSn") String eqptSn,
                         @Param("addTime") String addTime);

    //update
    Integer updateSmokeAlmSta(@Param("alarmId") String alarmId, @Param("almSta") int almSta);

    Integer updateMtInfo(MtUpdateVo mtUpdateVo);
    Integer updateMtSmokeInfo(MtUpdateVo mtUpdateVo);
}
