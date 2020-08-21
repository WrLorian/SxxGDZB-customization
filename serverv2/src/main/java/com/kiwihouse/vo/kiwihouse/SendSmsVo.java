package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.HashMap;
import java.util.List;

/**
 * @author yjzn
 * @date 2020-02-21-上午 11:52
 */
@ToString
@Getter
@Setter
@ApiModel("短信提示参数")
public class SendSmsVo {
    @ApiModelProperty(value = "设备序列号",name = "eqptSn",hidden = true)
    private String eqptSn;
    @ApiModelProperty(value = "告警信息",name = "almMsg")
    private String almMsg;
    @ApiModelProperty(value = "告警读数",name = "almValue",hidden = true)
    private String almValue;
    @ApiModelProperty(value = "地址(省市区详细地址)",name = "address",hidden = true)
    private String address;
    @ApiModelProperty(value = "电话号码",name = "phone")
    private String phone;
}
