package com.kiwihouse.service;

import java.util.List;
import java.util.Map;
public interface SysDictionaryService {
	/**
	 * 根据类型查找字典
	 * @param type
	 * @return
	 */
	List<Map> selectByType(String type);

}
