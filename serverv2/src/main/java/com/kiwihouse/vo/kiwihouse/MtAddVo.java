package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotBlank;

/**
 * @author yjzn
 * @date 2020-1-4 17:16:55
 */
@ToString
@Getter
@Setter
@ApiModel(description = "告警信息")
public class MtAddVo {

    @NotBlank(message = "eqptSn is not null")
    @ApiModelProperty(value = "设备序列号",name = "eqptSn",required = true)
    private String eqptSn;

    @NotBlank(message = "alarmMsg is not null")
    @ApiModelProperty(value = "告警json格式数据",name = "alarmMsg",required = true)
    private String alarmMsg;

}
