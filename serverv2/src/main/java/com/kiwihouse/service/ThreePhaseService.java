package com.kiwihouse.service;

import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;
import com.kiwihouse.vo.kiwihouse.ThreePhaseVo;

public interface ThreePhaseService {
	/**
	 *	 获取三相设备最新状态
	 * @param reportedQueryVo
	 * @return
	 */
	Response getLastStatus(ReportedQueryVo reportedQueryVo);
	/**
	 * 	查询三相信息
	 * @param tpv
	 * @return
	 */
	Response getMaxPowerList(ThreePhaseVo tpv);

}
