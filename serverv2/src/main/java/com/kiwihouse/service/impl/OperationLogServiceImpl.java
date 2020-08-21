package com.kiwihouse.service.impl;

import com.kiwihouse.dao.mapper.AuthOperationLogMapper;
import com.kiwihouse.dao.entity.AuthOperationLog;
import com.kiwihouse.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author tomsun28
 * @date 9:34 2018/4/22
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    @Autowired
    AuthOperationLogMapper authOperationLogMapper;

    @Override
    public List<AuthOperationLog> getOperationList() {
        return authOperationLogMapper.selectOperationLogList();
    }
}
