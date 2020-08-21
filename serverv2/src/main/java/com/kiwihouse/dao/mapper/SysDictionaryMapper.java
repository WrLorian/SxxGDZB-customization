package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.SysDictionary;

public interface SysDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysDictionary record);

    int insertSelective(SysDictionary record);

    SysDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysDictionary record);

    int updateByPrimaryKey(SysDictionary record);
    /**
     * 通过KEY查询
     * @param string
     * @return
     */
	String selectByKey(String string);
}