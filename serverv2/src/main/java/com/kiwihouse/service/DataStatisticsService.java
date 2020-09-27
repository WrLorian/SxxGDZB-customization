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

}
