package com.kiwihouse.service;

import java.util.List;

import com.kiwihouse.dao.entity.MainTainInfo;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.MtInfoVo;
import com.kiwihouse.vo.kiwihouse.MtUpdateVo;

public interface MaintainService {
	/**
	 * 查询维修信息信息
	 * @param mtInfoVo
	 * @return
	 */
	ResultList queryInfo(MtInfoVo mtInfoVo);
	/**
	 * 告警信息转工单
	 * @param alarmId
	 * @param eqptSn
	 * @param mtType
	 * @return
	 */
	Response addInfo(String alarmId, String eqptSn, String mtType);
	/**
	 * 修改维修信息
	 * @param mtUpdateVo
	 * @return
	 */
	Response updateInfo(MtUpdateVo mtUpdateVo);
	/**
	 * 批量添加 --->存在则修改
	 * @param userList
	 * @return
	 */
	Response insertOrUpdateBatch(List<MainTainInfo> userList);

}
