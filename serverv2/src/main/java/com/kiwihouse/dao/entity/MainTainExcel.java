package com.kiwihouse.dao.entity;

import com.kiwihouse.util.excel.Excel;

public class MainTainExcel {
	@Excel(name = "维修ID")
    private Integer mtId;
	@Excel(name = "告警ID")
    private Integer alarmId;
	@Excel(name = "设备序imei")
    private String imei;
	@Excel(name = "告警信息")
    private String mtMsg;
	@Excel(name = "状态")
    private Integer mtStatus;
	@Excel(name = "维修单名称")
    private String mtName;
	@Excel(name = "维修人员电话")
    private String mtPhone;

    public Integer getMtId() {
        return mtId;
    }

    public void setMtId(Integer mtId) {
        this.mtId = mtId;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMtMsg() {
        return mtMsg;
    }

    public void setMtMsg(String mtMsg) {
        this.mtMsg = mtMsg == null ? null : mtMsg.trim();
    }

    public Integer getMtStatus() {
        return mtStatus;
    }

    public void setMtStatus(Integer mtStatus) {
        this.mtStatus = mtStatus;
    }

    public String getMtName() {
        return mtName;
    }

    public void setMtName(String mtName) {
        this.mtName = mtName == null ? null : mtName.trim();
    }

    public String getMtPhone() {
        return mtPhone;
    }

    public void setMtPhone(String mtPhone) {
        this.mtPhone = mtPhone == null ? null : mtPhone.trim();
    }

	public MainTainExcel(Integer mtId, Integer alarmId, String imei, String mtMsg, Integer mtStatus, String mtName,
			String mtPhone) {
		super();
		this.mtId = mtId;
		this.alarmId = alarmId;
		this.imei = imei;
		this.mtMsg = mtMsg;
		this.mtStatus = mtStatus;
		this.mtName = mtName;
		this.mtPhone = mtPhone;
	}

	public MainTainExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MainTainInfo [mtId=" + mtId + ", alarmId=" + alarmId + ", imei=" + imei + ", mtMsg=" + mtMsg
				+ ", mtStatus=" + mtStatus + ", mtName=" + mtName + ", mtPhone=" + mtPhone + "]";
	}
    
    
}