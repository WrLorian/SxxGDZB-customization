package com.kiwihouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.dao.entity.DevInfo;
import com.kiwihouse.dao.mapper.DevInfoMapper;
import com.kiwihouse.dto.ReportedDto;
import com.kiwihouse.service.DevInfoService;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
@Service
public class DevInfoServiceImpl implements DevInfoService{
	@Autowired
	DevInfoMapper devInfoMapper;
	@Override
	public ResultList selectDevByNewTime(String imei) {
		// TODO Auto-generated method stub
		DevInfo devInfo = devInfoMapper.selectDevByNewTime(imei);
		if(devInfo == null) {
			return new ResultList(Code.QUERY_NULL.getCode(), Code.QUERY_NULL.getMsg(), null);
		}
		return new ResultList(Code.QUERY_SUCCESS.getCode(), Code.QUERY_SUCCESS.getMsg(), new Result<>(1, devInfo));
	}
}
