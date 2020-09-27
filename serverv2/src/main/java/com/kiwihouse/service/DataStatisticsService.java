package com.kiwihouse.service;

import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.DataStatisticsVo;

public interface DataStatisticsService {
	/**
	 * 数据总览所有人可查看
	 * @param dataStatisticsVo
	 * @return
	 */
	ResultList queryInfo(DataStatisticsVo dataStatisticsVo);
	/**
	 * 查询一个设备、一段时间的告警信息
	 * @param dataStatisticsVo
	 * @return
	 */
	ResultList queryInfoByImei(DataStatisticsVo dataStatisticsVo);

}
