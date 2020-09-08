package com.kiwihouse.service;

import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;
import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

public interface ThreePhaseService {
	/**
	 *	 获取三相设备最新状态
	 * @param reportedQueryVo
	 * @return
	 */
	ResultList getLastStatus(ReportedQueryVo reportedQueryVo);
	/**
	 * 	查询三相信息
	 * @param tpv
	 * @return
	 */
	ResultList getMaxPowerList(ThreePhaseVo tpv);

}
