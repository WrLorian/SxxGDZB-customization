package com.kiwihouse.domain.vo;

import java.util.Date;

public class AuthResourceVo {
	private Integer id;
	private String name;
	private String code;
	private String uri;
	private Integer type;
	private String method;
	private Integer status;
	private Date createTime;
    private Date updateTime;
    private String codeRes;
    
	public String getCodeRes() {
		return codeRes;
	}
	public void setCodeRes(String codeRes) {
		this.codeRes = codeRes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	@Override
	public String toString() {
		return "AuthResourceVo [id=" + id + ", name=" + name + ", code=" + code + ", uri=" + uri + ", type=" + type
				+ ", method=" + method + ", status=" + status + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
}
