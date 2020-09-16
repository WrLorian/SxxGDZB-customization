package com.kiwihouse.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.AlarmEqptDto;
import com.kiwihouse.dto.FirePwrDto;
import com.kiwihouse.dto.ReportedDto;
import com.kiwihouse.vo.kiwihouse.AlmQueryVo;
import com.kiwihouse.vo.kiwihouse.QueryPwrVo;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;

/**
 * @author yjzn
 * @date 2020-01-03-下午 3:25
 */
@Repository
public interface ReportedInfoMapper {

    //query
    List<ReportedDto> queryInfo(ReportedQueryVo reportedQueryVo);
    Integer queryInfoRow(ReportedQueryVo reportedQueryVo);

    List<AlarmEqptDto> queryAlmInfo(AlmQueryVo almQueryVo);

    List<FirePwrDto> queryPwr(QueryPwrVo queryPwrVo);
    
	Integer queryAlmInfoCount(AlmQueryVo almQueryVo);
	/**
	 * 	设备运行信息 ----> 替代alarmType = 3
	 * @param reportedQueryVo
	 * @return
	 */
	ReportedDto devRunInfo(ReportedQueryVo reportedQueryVo);
}
