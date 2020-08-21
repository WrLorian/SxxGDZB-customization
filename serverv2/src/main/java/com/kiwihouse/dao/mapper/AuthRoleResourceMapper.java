package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.AuthRoleResource;
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
}