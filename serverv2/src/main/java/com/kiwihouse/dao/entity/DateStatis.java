package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 数据总览
 * @author cmx
 *
 */
@ToString
@Setter
@Getter
public class DateStatis {
	/*用电告警器告警数量*/
	private int electricAlarms;
	/*用电告警器正常数量*/
	private int electricAlarm;
	/*过压告警  ---> type == 4*/
	private int overVoltageAlarm;
	/*过流告警  ---> type == 1*/
	private int overCurrentAlarm;
	/*过载告警  ---> type == 3*/
	private int overloadAlarms;
	/*欠压告警  ---> type == 5*/
	private int underVoltageAlarm;
	/*漏电告警  ---> type == 7*/
	private int leakageAlarm;
	/*温升告警 ---> type == 2*/
	private int temperatureAlarm;
	/*烟雾告警*/
	private int smokeAlarm;
	/*用电告警总故障数*/
	private int electricAlarmTotalFailure;
	/*掉电告警 ---> type == 6*/
	private int useingTheAlarm;
	/* 时间轴 */
	private String addTime;
}
