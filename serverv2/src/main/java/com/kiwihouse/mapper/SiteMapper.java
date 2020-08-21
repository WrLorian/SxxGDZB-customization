package com.kiwihouse.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.SiteDto;
import com.kiwihouse.vo.kiwihouse.SiteAddVo;
import com.kiwihouse.vo.kiwihouse.SiteQueryVo;

/**
 * @author yjzn
 * @date
 */
@Repository
public interface SiteMapper {

    //query
    List<SiteDto> queryInfo(SiteQueryVo siteQueryVo);
    Integer queryInfoRow(SiteQueryVo siteQueryVo);
    List<String> queryArea(String adminId);

    //add
    Integer addInfo(SiteAddVo siteAddVo);

    @Select("select eqpt_sn from equipment where site_id = #{siteId}")
    List<String> IsExistEqpt(String siteId);

    Integer deleteInfo(HashMap<String, String> map);
}
