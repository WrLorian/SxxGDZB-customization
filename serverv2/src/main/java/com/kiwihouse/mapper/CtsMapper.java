package com.kiwihouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.CtsDto;
import com.kiwihouse.vo.kiwihouse.CtsAddVo;
import com.kiwihouse.vo.kiwihouse.CtsQueryVo;
import com.kiwihouse.vo.kiwihouse.CtsUpdateVo;

/**
 * @author yjzn
 * @date 2020-03-12-下午 5:54
 */
@Repository
public interface CtsMapper {

    List<CtsDto> queryInfo(CtsQueryVo ctsQueryVo);
    Integer queryInfoRow(CtsQueryVo ctsQueryVo);

    Integer addInfo(CtsAddVo ctsAddVo);

    Integer updateInfo(CtsUpdateVo ctsUpdateVo);

    @Delete("delete from contacts where cts_id=#{ctsId}")
    Integer deleteInfo(String ctsId);
}
