package com.kiwihouse.dto.SqlProvider;

import org.apache.ibatis.jdbc.SQL;
import org.jetbrains.annotations.NotNull;

import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

/**
 * @author xin
 * @date 2020/4/30
 */

public class ThreePhaseMeasureProvider {
    public String getList(@NotNull ThreePhaseVo threePhaseVo) {
        return new SQL() {{
            SELECT("*");
            FROM("tp_measure");
            if (!threePhaseVo.getImei().isEmpty()) {
                WHERE("imei = #{imei} ");
            }
            if (!threePhaseVo.getBeginTime().isEmpty()) {
                WHERE("add_time >= #{beginTime}");
            }
            if (!threePhaseVo.getEndTime().isEmpty()) {
                WHERE("add_time<= #{endTime}");
            }
        }}.toString();
    }

    public String getListCount(@NotNull ThreePhaseVo threePhaseVo) {
        return new SQL() {{
            SELECT("COUNT(tp_measure_id)");
            FROM("tp_measure");
            if (!threePhaseVo.getImei().isEmpty()) {
                WHERE("imei = #{imei} ");
            }
            if (!threePhaseVo.getBeginTime().isEmpty()) {
                WHERE("add_time >= #{beginTime}");
            }
            if (!threePhaseVo.getEndTime().isEmpty()) {
                WHERE("add_time<= #{endTime}");
            }
        }}.toString();
    }

    public String getMaxPower(@NotNull ThreePhaseVo threePhaseVo) {
        return new SQL() {{
            SELECT("*");
            FROM("tp_pwr");
            WHERE("num > 0");
            if (!threePhaseVo.getImei().isEmpty()) {
                WHERE("imei = #{imei} ");
            }
            if (!threePhaseVo.getBeginTime().isEmpty()) {
                WHERE("add_time >= #{beginTime}");
            }
            if (!threePhaseVo.getEndTime().isEmpty()) {
                WHERE("add_time<= #{endTime}");
            }
            ORDER_BY("add_time");
        }}.toString();
    }
}
