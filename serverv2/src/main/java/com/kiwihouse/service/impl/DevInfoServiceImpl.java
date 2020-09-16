package com.kiwihouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.dao.entity.DevInfo;
import com.kiwihouse.dao.mapper.DevInfoMapper;
import com.kiwihouse.domain.vo.Response;
import com.kiwihouse.dto.ReportedDto;
import com.kiwihouse.service.DevInfoService;
import com.kiwihouse.vo.entire.Result;
import com.kiwihouse.vo.entire.ResultList;
@Service
public class DevInfoServiceImpl implements DevInfoService{
	@Autowired
	DevInfoMapper devInfoMapper;
	@Override
	public Response selectDevByNewTime(String imei) {
		// TODO Auto-generated method stub
		DevInfo devInfo = devInfoMapper.selectDevByNewTime(imei);
		if(devInfo == null) {
			return new Response().Success(Code.QUERY_NULL, Code.QUERY_NULL.getMsg());
		}
		return new Response().Success(Code.QUERY_SUCCESS, Code.QUERY_SUCCESS.getMsg()).addData("data", devInfo);
	}
}
