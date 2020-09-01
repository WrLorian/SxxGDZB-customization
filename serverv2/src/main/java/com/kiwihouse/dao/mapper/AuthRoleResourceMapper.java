package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.AuthRoleResource;
import com.kiwihouse.domain.vo.AuthRoleResourceVo;
import com.kiwihouse.domain.vo.AuthRoleMenuDetails;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

/**
 * @author tomsun28
 * @date 9:55 2018/4/22
 */
@Component
public interface AuthRoleResourceMapper {

    /**
     * description TODO
     *
     * @param id 1
     * @return int
     * @throws DataAccessException when
     */
    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int insert(AuthRoleResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int insertSelective(AuthRoleResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param id 1
     * @return com.kiwihouse.dao.entity.AuthRoleResource
     * @throws DataAccessException when
     */
    AuthRoleResource selectByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKeySelective(AuthRoleResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKey(AuthRoleResource record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param roleId 1
     * @param resourceId 2
     * @return int
     * @throws DataAccessException when
     */
    int deleteByUniqueKey(@Param("roleId") Integer roleId,@Param("resourceId") Integer resourceId) throws DataAccessException;
    /**
     * 获取角色对应资源列表记录总数
     * @param roleId
     * @param auResourceVo
     * @return
     */
	int selectRoleResourceCount(	
			@Param("roleId") Integer roleId, 
			@Param("auRoleResourceVo") AuthRoleResourceVo auRoleResourceVo);
	/**
	 * 获取角色对应资源列表
	 * @param i
	 * @param limit
	 * @param roleId
	 * @param auResourceVo
	 * @return
	 */
	List<AuthRoleMenuDetails> selectRoleResourceList(
			@Param("page") Integer page,
			@Param("limit") Integer limit,
			@Param("roleId") Integer roleId, 
			@Param("auRoleResourceVo") AuthRoleResourceVo auRoleResourceVo);
	/**
	 * 批量添加  角色资源列表
	 * @param list
	 * @return
	 */
	int insertBatch(List<AuthRoleResource> list);
	/**
	 * 批量删除 角色资源列表
	 * @param list
	 * @return
	 */
	int deleteBatch(String [] ids);
	/**
	 * 批量添加或更新资源列表
	 * @param list
	 * @return
	 */
	int insertOrUpdateBatch(List<AuthRoleResource> list);
	/**
	 * 根据角色id删除 资源列表
	 * @param roleId
	 */
	void deleteByRoleId(Integer roleId);
	/**
	 * 根据roles code查询角色权限
	 * @param roles
	 * @return
	 */
	String selectRoleRulesByRole(String roles);
}