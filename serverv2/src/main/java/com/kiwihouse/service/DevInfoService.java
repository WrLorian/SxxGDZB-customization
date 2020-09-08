package com.kiwihouse.service;

import com.kiwihouse.vo.entire.ResultList;

public interface DevInfoService {
	/**
	 *	 查询某个设备最新的上报信息
	 * @param imei
	 * @return
	 */
	ResultList selectDevByNewTime(String imei);

}
