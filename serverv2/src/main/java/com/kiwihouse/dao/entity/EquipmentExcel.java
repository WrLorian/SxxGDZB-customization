package com.kiwihouse.dao.entity;

import java.util.Date;

import com.kiwihouse.util.excel.Excel;
import com.kiwihouse.util.excel.Excel.Type;

public class EquipmentExcel {
	@Excel(name = "设备ID")
    private Integer eqptId;
	@Excel(name = "设备名称")
    private String eqptName;
	@Excel(name = "设备imei")
    private String imei;
	@Excel(name = "设备imsi")
    private String imsi;
	@Excel(name = "设备序列号")
    private String eqptSn;
	@Excel(name = "设备类型",readConverterExp = "0=单相用电设备,1=三相用电设备,2=烟感告警器")
    private String eqptType;
	@Excel(name = "是否注册到onenet平台",defaultValue = "0",readConverterExp = "0=否,1=是")
    private Short electricity;
	@Excel(name= "省")
	private String province;
	@Excel(name= "市")
    private String city;
	@Excel(name= "区")
    private String district;
    private Byte register;
    @Excel(name= "设备详细地址")
    private String eqptAddr;
    @Excel(name= "纬度")
    private String latitude;
    @Excel(name= "经度")
    private String longitude;
    @Excel(name = "用户ID",defaultValue = "0")
    private Integer userId;
    @Excel(name = "小区ID",defaultValue = "0")
    private Integer siteId;
    @Excel(name = "分组ID",defaultValue = "0")
    private Integer groupId;

//    private Date lastReportTime;
    @Excel(name = "添加时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date addTime;

    private String iccid;

    private String imgs;

    
    
    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getEqptId() {
        return eqptId;
    }

    public void setEqptId(Integer eqptId) {
        this.eqptId = eqptId;
    }


    public String getEqptName() {
        return eqptName;
    }

    public void setEqptName(String eqptName) {
        this.eqptName = eqptName == null ? null : eqptName.trim();
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    public String getEqptSn() {
        return eqptSn;
    }

    public void setEqptSn(String eqptSn) {
        this.eqptSn = eqptSn == null ? null : eqptSn.trim();
    }

    public String getEqptType() {
        return eqptType;
    }

    public void setEqptType(String eqptType) {
        this.eqptType = eqptType == null ? null : eqptType.trim();
    }

    public Short getElectricity() {
        return electricity;
    }

    public void setElectricity(Short electricity) {
        this.electricity = electricity;
    }

    public Byte getRegister() {
        return register;
    }

    public void setRegister(Byte register) {
        this.register = register;
    }

    public String getEqptAddr() {
        return eqptAddr;
    }

    public void setEqptAddr(String eqptAddr) {
        this.eqptAddr = eqptAddr == null ? null : eqptAddr.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }


    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

//    public Date getLastReportTime() {
//        return lastReportTime;
//    }
//
//    public void setLastReportTime(Date lastReportTime) {
//        this.lastReportTime = lastReportTime;
//    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid == null ? null : iccid.trim();
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs == null ? null : imgs.trim();
    }

	public EquipmentExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EquipmentExcel(
			Integer eqptId, 
			String eqptName, 
			String imei, 
			String imsi, 
			String eqptSn,
			String eqptType, 
			Short electricity,
			Byte register, 
			String eqptAddr,
			String latitude, 
			String longitude, 
			Integer userId, 
			Integer siteId, 
			Integer groupId,
			Date addTime, 
			String iccid, 
//			String imgs, 
			String province, 
			String city, 
			String district) {
		super();
		this.eqptId = eqptId;
		this.eqptName = eqptName;
		this.imei = imei;
		this.imsi = imsi;
		this.eqptSn = eqptSn;
		this.eqptType = eqptType;
		this.electricity = electricity;
		this.register = register;
		this.eqptAddr = eqptAddr;
		this.latitude = latitude;
		this.longitude = longitude;
		this.userId = userId;
		this.siteId = siteId;
		this.groupId = groupId;
		this.addTime = addTime;
		this.iccid = iccid;
//		this.imgs = imgs;
		this.province = province;
		this.city = city;
		this.district = district;
	}
    
    
}