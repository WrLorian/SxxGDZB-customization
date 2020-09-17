package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression.DateTime;

@Setter
@Getter
@ToString
public class TpMeasure {

	/*三相设备测量数据ID*/
	private Integer tpMeasureId ;
	/*A相电压*/
	private float volA;
	/*B相电压*/
	private float volB;
	/*C相电压*/
	private float volC;
	/*A相电流*/
	private float curA;
	/*B相电流*/
	private float curB;
	/*C相电流*/
	private float curC;
	/*A相功率*/
	private float pwrA;
	/*B相功率*/
	private float pwrB;
	/*C相功率*/
	private float pwrC;
	/*电能	*/
	private double kwh;
	/*线温*/
	private int lineTemp;
	/*漏电流*/
	private int leakCur;
	/*A相功率因数*/
	private double pwrFctA;
	/*B相功率因数*/
	private double pwrFctB;
	/*C相功率因数*/
	private double pwrFctC;
	/*信号*/
	private int csp;
	/*数据录入时间*/
	private DateTime addTime;
	
	private String imei;
}
