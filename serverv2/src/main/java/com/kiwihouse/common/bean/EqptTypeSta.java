package com.kiwihouse.common.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020年1月13日10:29:56
 */
@ToString
@Getter
@Setter
public class EqptTypeSta {

	/**
	 * 电感
	 */
    public final static String DX = "0";
    /**
     * 烟感
     */
    public final static String YG = "1";
    /**
     * 三相设备
     */
    public final static String SX = "2";

}
