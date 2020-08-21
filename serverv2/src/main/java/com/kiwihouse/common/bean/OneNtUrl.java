package com.kiwihouse.common.bean;

/**
 * @author yjzn
 * @date 2020-1-9 13:52:16
 */
public class OneNtUrl {

    /**
     * onenet注册设备URL
     */
    public static final String REGISTER = "http://api.heclouds.com/devices";
    /**
     * 查询设备状态
     * http://api.heclouds.com/devices/{deviceId}
     */
    public static final String querySta = "http://api.heclouds.com/devices/";
    /**
     * 根据IMEI号写设备资源
     * "http://api.heclouds.com/nbiot?obj_id=3300&obj_inst_id=0&mode=1&timeout=25&imei=865933030000000"
     */
    public static final String writeResource = "http://api.heclouds.com/nbiot?obj_id=3300&obj_inst_id=0&mode=1&timeout=25&imei=";

}
