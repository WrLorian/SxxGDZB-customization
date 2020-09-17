package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Getter
@Setter
@ToString
public class TpThreshold {
	private int tp_threshold_id;  //是 三相设备阀值信息
	private int ct;  //否 电流变比
	private int beep;  //否 蜂鸣器模式(0：关闭，1：开启)
	private int volH ; //否 电压阀值
	private int volL ; //否 电压最小值
	private int leakH ; //否 电压最小值
	private int curH ; //否 电流阀值
	private int pwrH; //否 功率阀值
	private int tempH ; //否 线温阀值
	private int lct ; //否 漏电流变比
	private int interval;  //否 数据上报周期
	private DateTime add_time ; //是 数据录入时间
	private DateTime update_time ; //否 数据更新时间
}
