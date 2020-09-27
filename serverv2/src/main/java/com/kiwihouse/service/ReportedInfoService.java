package com.kiwihouse.service;

import java.text.ParseException;
import java.util.Map;

import com.kiwihouse.domain.vo.Response;
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
	Map<String, Object>  queryAlmInfo(AlmQueryVo almQueryVo);
	/**
	 * 	查询设备上报功率数据
	 * @param queryPwrVo
	 * @return
	 */
	Response queryPwr(QueryPwrVo queryPwrVo) throws ParseException;
	/**
	 * 设备参数信息
	 * @param reportedQueryVo
	 * @param eqptType 数据类型
	 * @return
	 */
	Response devRunInfo(ReportedQueryVo reportedQueryVo,Integer type);
	/**
	 * 	实时数据
	 * @param reportedQueryVo
	 * @return
	 */
	Response devRealDate(ReportedQueryVo reportedQueryVo,Integer type);

}
