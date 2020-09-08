package com.kiwihouse.service;

import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.AlmQueryVo;
import com.kiwihouse.vo.kiwihouse.QueryPwrVo;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;

public interface ReportedInfoService {
	/**
	 * 	查询设备上报信息
	 * @param reportedQueryVo
	 * @return
	 */
	ResultList queryInfo(ReportedQueryVo reportedQueryVo);
	/**
	 * 	查询设备告警信息
	 * @param almQueryVo
	 * @return
	 */
	ResultList queryAlmInfo(AlmQueryVo almQueryVo);
	/**
	 * 	查询设备上报功率数据
	 * @param queryPwrVo
	 * @return
	 */
	ResultList queryPwr(QueryPwrVo queryPwrVo);

}
