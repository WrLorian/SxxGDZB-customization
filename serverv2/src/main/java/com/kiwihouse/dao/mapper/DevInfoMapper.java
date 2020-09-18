package com.kiwihouse.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.kiwihouse.dao.entity.DevInfo;

public interface DevInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DevInfo record);

    int insertSelective(DevInfo record);

    DevInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DevInfo record);

    int updateByPrimaryKey(DevInfo record);
    
    /**
	 * 	批量添加或更新资源列表
	 * @param list
	 * @return
	 */
	int insertOrUpdateBatch(List<DevInfo> list);
	/**
	 * 	查询某个设备最新的上报信息
	 * @param reportedQueryVo
	 * @return
	 */
	DevInfo selectDevByNewTime(@Param("imei") String imei,@Param("type") Integer type);
	
	
}