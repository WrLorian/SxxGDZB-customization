package com.kiwihouse.service;



import com.kiwihouse.dao.entity.AuthAccountLog;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:30 2018/4/22
 */
public interface AccountLogService {

    /**
     * description TODO
     *
     * @return java.util.List<AuthAccountLog>
     */
    List<AuthAccountLog> getAccountLogList();
}
