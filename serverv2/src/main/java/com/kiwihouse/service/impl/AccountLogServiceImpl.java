package com.kiwihouse.service.impl;

import com.kiwihouse.dao.mapper.AuthAccountLogMapper;
import com.kiwihouse.dao.entity.AuthAccountLog;
import com.kiwihouse.service.AccountLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author tomsun28
 * @date 9:32 2018/4/22
 */
@Service
public class AccountLogServiceImpl implements AccountLogService {

    @Autowired
    AuthAccountLogMapper authAccountLogMapper;

    @Override
    public List<AuthAccountLog> getAccountLogList() {
        return authAccountLogMapper.selectAccountLogList();
    }
}
