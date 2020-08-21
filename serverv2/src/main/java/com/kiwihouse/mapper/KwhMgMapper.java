package com.kiwihouse.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.ElectricEnergy;
import com.kiwihouse.dto.MeterNowDto;

/**
 * @author yjzn
 * @date
 */
@Repository
public interface KwhMgMapper {

    Integer updateGroup(@Param("groupId") String groupId,
                       @Param("cron") String cron);

    List<String> queryGroups(String adminId);

    List<ElectricEnergy> queryInfo(HashMap<String, Object> map);
    Integer queryInfoRow(HashMap<String, Object> map);

    MeterNowDto queryMeterInfo(String eqptSn);
    Integer insertMeterInfo(MeterNowDto meterNowDto);
}
