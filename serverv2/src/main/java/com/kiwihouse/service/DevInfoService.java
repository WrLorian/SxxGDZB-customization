package com.kiwihouse.service;

import com.kiwihouse.domain.vo.Response;

public interface DevInfoService {
	/**
	 *	 查询某个设备最新的上报信息
	 * @param imei
	 * @return
	 */
	Response selectDevByNewTime(String imei,Integer type);

}
