package com.kiwihouse.service;

import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.kiwihouse.domain.vo.AuthRoleResourceVo;

public interface AuthRoleResourceService {
	/**
	 * 获取角色对应资源列表
	 * @param page
	 * @param limit
	 * @param roleId
	 * @param auResourceVo
	 * @return
	 */
	Map<String, Object> getRoleResourceList(Integer page, Integer limit, Integer roleId, AuthRoleResourceVo auResourceVo);
	/**
	 * 增加角色资源
	 * @param auResourceVo
	 * @return
	 */
	Boolean addAuthRoleResource(AuthRoleResourceVo auResourceVo);
	/**
	 * 删除角色资源
	 * @param resIds
	 * @param roleIds
	 * @return
	 */
	Boolean deleteBatch( String ids);
	/**
	 * 修改角色资源
	 * @param roleId
	 * @param resIds
	 * @return
	 */
	Boolean updAuthRoleResource(Integer roleId, String resIds);

}
