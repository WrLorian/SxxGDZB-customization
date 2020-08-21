package com.kiwihouse.service.schedule;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.kiwihouse.common.utils.CrudUtil;


/**
 * @author yjzn
 * @date 2020-1-14 14:24:05
 */
public class MeterKwh extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        String adminId = mergedJobDataMap.getString("dz-usr");
        String groupId = mergedJobDataMap.getString("groupId");
        ArrayList<LinkedHashMap<Object, Object>> list = CrudUtil.queryKwh(groupId);
        CrudUtil.BatchInsert(list);
    }

}


