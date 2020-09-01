package com.kiwihouse.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.kiwihouse.dto.GroupDto;
import com.kiwihouse.vo.kiwihouse.GroupAddVo;
import com.kiwihouse.vo.kiwihouse.GroupQueryVo;
import com.kiwihouse.vo.kiwihouse.GroupUpdateVo;

/**
 * @author yjzn
 * @date 2020-03-05-下午 3:53
 */
@Repository
public interface GroupMapper {
    List<GroupDto> queryInfo(GroupQueryVo groupQueryVo);
    Integer queryInfoRow(GroupQueryVo groupQueryVo);

    Integer addInfo(GroupAddVo groupAddVo);

    @Delete("delete from `group` where group_id = #{groupId}")
    Integer toDelete(String groupId);

    @Select("select group_id from `group` where admin_id = #{adminId}")
    List<String> queryGroups(String adminId);

    @Select("select eqpt_id from equipment where group_id = #{groupId}")
    List<String> queryEqpts(String groupId);

    Integer updateGroup(GroupUpdateVo groupUpdateVo);

    Integer updateEqpt(GroupUpdateVo groupUpdateVo);

    Integer updateGroupIds(List<HashMap<String,Object>> groupIds);
    /**
     * 	查询分组信息
     * @param groupAddVo
     * @return
     */
    GroupDto selectOneInfo(GroupQueryVo groupQueryVo);
}
