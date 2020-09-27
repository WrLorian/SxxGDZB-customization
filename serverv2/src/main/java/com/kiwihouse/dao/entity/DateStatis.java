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
	private String electricAlarms;
	/*用电告警器正常数量*/
	private String electricAlarm;
	/*过压告警  ---> type == 4*/
	private String overVoltageAlarm;
	/*过流告警  ---> type == 1*/
	private String overCurrentAlarm;
	/*过载告警  ---> type == 3*/
	private String overloadAlarms;
	/*欠压告警  ---> type == 5*/
	private String underVoltageAlarm;
	/*漏电告警  ---> type == 7*/
	private String leakageAlarm;
	/*温升告警 ---> type == 2*/
	private String temperatureAlarm;
	/*烟雾告警*/
	private String smokeAlarm;
	/*用电告警总故障数*/
	private String electricAlarmTotalFailure;
	/*掉电告警 ---> type == 6*/
	private String useingTheAlarm;
	/* 时间轴 */
	private String addTime;
}
