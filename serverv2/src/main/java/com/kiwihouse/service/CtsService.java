package com.kiwihouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiwihouse.mapper.CtsMapper;
import com.kiwihouse.util.ResultUtil;
import com.kiwihouse.vo.entire.ResultList;
import com.kiwihouse.vo.kiwihouse.CtsAddVo;
import com.kiwihouse.vo.kiwihouse.CtsQueryVo;
import com.kiwihouse.vo.kiwihouse.CtsUpdateVo;

/**
 * @author yjzn
 * @date 2020-03-12-下午 5:53
 */
@Service
public class CtsService {

    @Autowired
    CtsMapper ctsMapper;

    /**
     * 查询联系人信息
     */
    public ResultList queryInfo(CtsQueryVo ctsQueryVo,boolean isTopMg) {
        return ResultUtil.verifyQuery(ctsMapper.queryInfo(ctsQueryVo),ctsMapper.queryInfoRow(ctsQueryVo));
    }

    /**
     * 添加联系人信息
     */
    public ResultList addInfo(CtsAddVo ctsAddVo) {
        return ResultUtil.verifyAdd(ctsMapper.addInfo(ctsAddVo));
    }

    /**
     * 修改联系人信息
     */
    public ResultList updateInfo(CtsUpdateVo ctsUpdateVo) {
        return ResultUtil.verifyUpdate(ctsMapper.updateInfo(ctsUpdateVo));
    }

    /**
     * 删除联系人信息
     */
    public ResultList deleteInfo(String ctsId) {
        return ResultUtil.verifyDelete(ctsMapper.deleteInfo(ctsId));
    }
}
