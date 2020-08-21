package com.kiwihouse.dao.mapper;

import com.kiwihouse.dao.entity.AuthRole;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:40 2018/4/22
 */
@Component
public interface AuthRoleMapper {
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
    int insert(AuthRole record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int insertSelective(AuthRole record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param id 1
     * @return com.kiwihouse.dao.entity.AuthRole
     * @throws DataAccessException when
     */
    AuthRole selectByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKeySelective(AuthRole record) throws DataAccessException;

    /**
     * description TODO
     *
     * @param record 1
     * @return int
     * @throws DataAccessException when
     */
    int updateByPrimaryKey(AuthRole record) throws DataAccessException;

    /**
     * description TODO
     * @return java.util.List<com.kiwihouse.dao.entity.AuthRole>
     * @throws DataAccessException when
     */
    List<AuthRole> selectRoles() throws DataAccessException;
}