package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date
 */
@ToString
@Getter
@Setter
@ApiModel(description = "查询分组")
public class GroupQueryVo extends Basic {

    @ApiModelProperty(value = "是否存在定时抄表任务",name = "cron")
    private String cron;

    @ApiModelProperty(value = "分组名称",name = "groupName")
    private String groupName;


    @Min(value = 1,message = "page is more than 1")
    @NotNull(message = "page is not null")
    @ApiModelProperty(value = "页码",name = "page",example = "1")
    private int page;


    @Min(value = 1,message = "limit is more than 1")
    @NotNull(message = "limit is not null")
    @ApiModelProperty(value = "每页条数",name = "limit",example = "10")
    private int limit;




    @ApiModelProperty(hidden = true)
    private String province;
    @ApiModelProperty(hidden = true)
    private String city;
    @ApiModelProperty(hidden = true)
    private String district;

}
