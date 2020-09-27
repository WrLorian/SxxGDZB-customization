package com.kiwihouse.service;

import java.util.List;
import java.util.Map;

import com.kiwihouse.dao.entity.SysDictionary;
public interface SysDictionaryService {
	/**
	 * 根据类型查找字典
	 * @param type
	 * @return
	 */
	List<Map> selectByType(String type);
	/**
	 * 根据类型和Key值查找字典
	 * @param type
	 * @param key
	 * @return
	 */
	SysDictionary selectByTypeAndKey(String type, String key);

}
