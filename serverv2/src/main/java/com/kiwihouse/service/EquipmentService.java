package com.kiwihouse.service;

import java.util.Map;

import com.kiwihouse.common.bean.UserInfo;
import com.kiwihouse.dao.entity.AuthUser;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.Eqpt4UpdateDto;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.EqptAddVo;
import com.kiwihouse.vo.kiwihouse.EqptQueryVo;

public interface EquipmentService {
	/**
	 * 	查询设备列表
	 * @param eqptQueryVo
	 * @param authUser
	 * @return
	 */
	Map<String, Object> queryInfo(EqptQueryVo eqptQueryVo, AuthUser authUser);
	/**
	 * 	更新设备信息
	 * @param updateDto
	 * @return
	 */
	Response updateInfo(Eqpt4UpdateDto updateDto);
	/**
	 * 	录入设备信息
	 * @param eqptAddVo
	 * @param userInfo
	 * @return
	 */
	Response addInfo(EqptAddVo eqptAddVo);
	/**
	 * 	删除设备信息
	 * @param eqptSn
	 * @param userInfo
	 * @return
	 */
	Response deleteInfo(String imeis, UserInfo userInfo);
	/**
	 * 查询单个设备信息
	 * @param eqptQueryVo
	 * @return
	 */
	Response selectOneInfo(EqptQueryVo eqptQueryVo);

}
