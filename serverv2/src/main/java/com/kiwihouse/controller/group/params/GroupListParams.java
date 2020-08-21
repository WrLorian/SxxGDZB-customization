package com.kiwihouse.controller.group.params;

import com.kiwihouse.controller.common.PageParams;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xin
 * @date 2020/7/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupListParams extends PageParams {
    @ApiParam(name = "是否存在抄表任务")
    Boolean cron;


    @ApiParam(name = "分组名")
    String name;

}
