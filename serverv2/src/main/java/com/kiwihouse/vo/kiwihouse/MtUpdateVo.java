package com.kiwihouse.vo.kiwihouse;

import javax.validation.constraints.NotNull;

import com.kiwihouse.common.annotation.naturalNumber.NaturalNumber;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-7 20:51:49
 */
@ToString
@Getter
@Setter
@ApiModel(description = "更新维修记录参数")
public class MtUpdateVo {

    @ApiModelProperty(value = "工单ID",name = "mtId",required = true)
    private String mtId;
    @ApiModelProperty(value = "告警Id",name = "alarmId",required = true)
    private String alarmId;
    @ApiModelProperty("维修人姓名")
    private String mtName;
    @ApiModelProperty("维修人电话")
    private String mtPhone;
    @ApiModelProperty("维修记录")
    private String mtMsg;

    @ApiModelProperty("维修状态")
    private String mtStatus;

    @ApiModelProperty(value = "维修工单类型(1:单相用电设备，2:三相用电设备 3、烟感设备)",name = "mtType",required = true)
    private String mtType;
}
