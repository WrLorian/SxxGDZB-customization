package com.kiwihouse.dao.entity;

import java.util.Date;

/**
 * <p>
 * 告警信息表
 * </p>
 *
 * @author sxx
 * @since 2020-09-08
 */
public class DevInfo {

    /**
     * 	设备数据ID
     */
	private Integer id;
    /**
     * json格式设备数据
     */
	private String dataJson;
    /**
     * 	设备IMEI号
     */
	private String imei;
    /**
     *	 数据录入时间
     */
	private String addTime;
	/**
	 * 	设备类型
	 */
	private String type;

	private Integer eqptType;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getEqptType() {
		return eqptType;
	}

	public void setEqptType(Integer eqptType) {
		this.eqptType = eqptType;
	}
	
}
