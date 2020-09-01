package com.kiwihouse.service;

import java.util.Map;

import com.kiwihouse.common.bean.UserInfo;
import com.kiwihouse.dao.entity.AuthUser;
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
	ResultList updateInfo(Eqpt4UpdateDto updateDto);
	/**
	 * 	录入设备信息
	 * @param eqptAddVo
	 * @param userInfo
	 * @return
	 */
	ResultList addInfo(EqptAddVo eqptAddVo, UserInfo userInfo);
	/**
	 * 	删除设备信息
	 * @param eqptSn
	 * @param userInfo
	 * @return
	 */
	ResultList deleteInfo(String eqptSn, UserInfo userInfo);

}
