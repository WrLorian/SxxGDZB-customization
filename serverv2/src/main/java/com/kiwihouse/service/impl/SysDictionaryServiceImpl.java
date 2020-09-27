package com.kiwihouse.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.dao.entity.SysDictionary;
import com.kiwihouse.dao.mapper.SysDictionaryMapper;
import com.kiwihouse.service.SysDictionaryService;
@Service
public class SysDictionaryServiceImpl implements SysDictionaryService{

	@Autowired
	SysDictionaryMapper SysDictionaryMapper;
	@Override
	public List<Map> selectByType(String type) {
		// TODO Auto-generated method stub
		return SysDictionaryMapper.selectByType(type);
	}
	@Override
	public SysDictionary selectByTypeAndKey(String type, String key) {
		// TODO Auto-generated method stub
		return SysDictionaryMapper.selectByTypeAndKey(type,key);
	}

}
