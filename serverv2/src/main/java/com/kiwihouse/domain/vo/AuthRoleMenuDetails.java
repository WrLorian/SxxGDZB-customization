package com.kiwihouse.domain.vo;
/**
 * 用户菜单权限entity
 * @author cmx
 *
 */
public class AuthRoleMenuDetails {
	/**
	 * 角色ID
	 */
	private Integer roleId;
	/**
	 * 菜单名称
	 */
	private String name;
	/**
	 * 菜单路径
	 */
	private String url;
	/**
	 * 角色编码
	 */
	private String code;
	/**
	 * 菜单ID
	 */
	private Integer id;
	/**
	 * 父节点ID
	 */
	private Integer parentId;
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
