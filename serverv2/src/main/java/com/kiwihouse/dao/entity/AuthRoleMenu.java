package com.kiwihouse.dao.entity;


/**
 * <p>
 * 
 * </p>
 *
 * @author sxx
 * @since 2020-08-14
 */
public class AuthRoleMenu {


	private Integer roleId;
	private Integer menuId;
	private String ids;
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "AuthRoleMenu [roleId=" + roleId + ", menuId=" + menuId + ", ids=" + ids + "]";
	}


}
