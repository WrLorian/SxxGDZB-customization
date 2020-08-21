package com.kiwihouse.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.SmokeDBInfo;
import com.kiwihouse.vo.kiwihouse.SmokeReportedQueryVo;

/**
 * @author yjzn
 * @date
 */
@Repository
public interface SmokeDevReportInfoMapper {
    List<SmokeDBInfo> queryInfo(SmokeReportedQueryVo smokeReportedQueryVo);
    Integer queryInfoRow(SmokeReportedQueryVo smokeReportedQueryVo);
}
