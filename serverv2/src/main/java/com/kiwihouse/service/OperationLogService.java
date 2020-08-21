package com.kiwihouse.service;


import com.kiwihouse.dao.entity.AuthOperationLog;

import java.util.List;

/**
 * @author tomsun28
 * @date 9:30 2018/4/22
 */
public interface OperationLogService {

    /**
     * description TODO
     *
     * @param  1
     * @return java.util.List<com.kiwihouse.dao.entity.AuthOperationLog>
     */
    List<AuthOperationLog> getOperationList();
}
