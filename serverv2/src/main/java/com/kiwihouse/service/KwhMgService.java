package com.kiwihouse.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kiwihouse.vo.entire.ResultList;

public interface KwhMgService {
	/**
	 * 	开始抄表,从当前时间开始
	 * @param schedule
	 * @param tod
	 * @param groupId
	 * @param adminId
	 * @return
	 */
	ResultList meterInfo(String schedule, String tod, String groupId, String adminId);
	/**
	 * 	立即抄表
	 * @param list
	 * @return
	 */
	ResultList meterNow(List<String> list);
	/**
	 * 	停止抄表业务
	 * @param groupId
	 * @param roleId
	 * @return
	 */
	ResultList stopInfo(String groupId, String roleId);
	/**
	 *	 查询抄表记录
	 * @param groupId
	 * @param eqptSn
	 * @param page
	 * @param limit
	 * @param request
	 * @return
	 */
	Map<String, Object> queryInfo(String groupId, String eqptSn, Integer page, Integer limit, HttpServletRequest request);

}
