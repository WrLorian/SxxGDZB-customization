package com.kiwihouse.common.utils;

/**
 * @author yjzn
 * @date 2020-1-14 15:47:50
 */
public class CronUtil {

    /**
     * 根据日期、月份、时间生成对应的cron表达式 ==> 没年循环
     * @param schedule 日期和月份
     * @param tod Time Of Day
     * @return 对应的cron表达式
     */
    public static String ToCron(String schedule,String tod){

        String[] tods = tod.split(":");
        String[] schedules = schedule.split("-");
        StringBuilder croExp=new StringBuilder();

        StringBuilder append = croExp.append(tods[2]) //秒
                .append(" ")
                .append(tods[1])   //分
                .append(" ")
                .append(tods[0])   //小时
                .append(" ")
                .append(schedules[1])
                .append(" ")
                .append(schedules[0])
                .append(" ?");

        return append.toString();
    }

}
