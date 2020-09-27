package com.kiwihouse.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kiwihouse.dao.entity.Alarm;
import com.kiwihouse.dao.entity.DataTimeNum;
import com.kiwihouse.dto.AlarmEqptDto;
import com.kiwihouse.vo.kiwihouse.AlmQueryVo;
import com.kiwihouse.vo.kiwihouse.DataStatisticsVo;

public interface AlarmMapper {
    int deleteByPrimaryKey(Integer alarmId);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Integer alarmId);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);
    /**
     * 批量添加
     * @param listAlarm
     */
	void insertBatch(List<Alarm> listAlarm);
	
	/**
	 * 查询告警记录
	 * @param almQueryVo
	 * @return
	 */
	List<AlarmEqptDto> queryAlarm(AlmQueryVo almQueryVo);
	/**
	 * 查询告警记录总数
	 * @param almQueryVo
	 * @return
	 */
	int queryAlarmCount(AlmQueryVo almQueryVo);
	
	Integer updateAlmSta(@Param("alarmId") String alarmId,@Param("almSta") int almSta);
	/**
	 * 时间范围内 设备告警数量 
	 * @param startTime
	 * @param startTime2
	 * @param string
	 * @return
	 */
	int timeAlarmCount(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("eqptType") String eqptType);
	
	List<DataTimeNum> selectByTimesAndType(@Param("dataStatisticsVo") DataStatisticsVo dataStatisticsVo,@Param("alarmType") int alarmType);
}