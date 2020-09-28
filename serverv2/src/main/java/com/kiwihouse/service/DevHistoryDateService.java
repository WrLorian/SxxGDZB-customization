package com.kiwihouse.service;

import java.util.Map;

import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;

public interface DevHistoryDateService {
	/**
	 * 查询设备历史记录 ----> 统计
	 * @param reportedQueryVo
	 * @return
	 */
	Response historyDevInfo(ReportedQueryVo reportedQueryVo);
	/**
	 * 查询设备历史记录 -----> 列表
	 * @param reportedQueryVo
	 * @return
	 */
	Map<String, Object> selectAll(ReportedQueryVo reportedQueryVo);

}
