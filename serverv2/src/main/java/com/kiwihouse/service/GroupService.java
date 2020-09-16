package com.kiwihouse.service;

import java.util.Map;

import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.GroupDto;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.GroupAddVo;
import com.kiwihouse.vo.kiwihouse.GroupQueryVo;
import com.kiwihouse.vo.kiwihouse.GroupUpdateVo;

public interface GroupService {
	/**
	 * 	查询分组列表
	 * @param groupQueryVo
	 * @return
	 */
	Map<String, Object> queryInfo(GroupQueryVo groupQueryVo);
	/**
	 * 	查询一条分组信息
	 * @param groupQueryVo
	 * @return
	 */
	GroupDto selectOneInfo(GroupQueryVo groupQueryVo);
	/**
	 * 	添加分组信息
	 * @param groupAddVo
	 * @return
	 */
	Response addInfo(GroupAddVo groupAddVo);
	/**
	 *	 修改分组信息
	 * @param groupUpdateVo
	 * @return
	 */
	ResultList updateInfo(GroupUpdateVo groupUpdateVo);
	/**
	 * 	删除空分组
	 * @param groupId
	 * @param roleId
	 * @param eqptIds 
	 * @return
	 */
	ResultList deleteInfo(String groupId, Integer roleId, String eqptIds);

}
