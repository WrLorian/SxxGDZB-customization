package com.kiwihouse.dao.mapper;

import java.util.List;

import com.kiwihouse.dao.entity.DevHistoryDate;
import com.kiwihouse.vo.kiwihouse.ReportedQueryVo;

public interface DevHistoryDateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DevHistoryDate record);

    int insertSelective(DevHistoryDate record);

    DevHistoryDate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DevHistoryDate record);

    int updateByPrimaryKey(DevHistoryDate record);
    /**
     * 查询历史记录
     * @param reportedQueryVo
     * @return
     */
	List<DevHistoryDate> historyDevInfo(ReportedQueryVo reportedQueryVo);
	/**
	 * 查询历史记录 ---> 条数
	 * @param reportedQueryVo
	 * @return
	 */
	Integer historyDevInfoCount(ReportedQueryVo reportedQueryVo);
}