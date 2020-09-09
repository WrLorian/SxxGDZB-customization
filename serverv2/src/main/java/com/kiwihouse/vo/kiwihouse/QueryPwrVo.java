package com.kiwihouse.vo.kiwihouse;

import com.kiwihouse.common.annotation.time.Time;
import com.kiwihouse.common.bean.Basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 陈伟平
 * @date 2020-03-30-下午 1:39
 */
@ToString
@Getter
@Setter
@ApiModel(description = "查询功率参数")
public class QueryPwrVo extends Basic {

    @ApiModelProperty(value = "设备序列号", name = "eqptSn", required = true)
    private String eqptSn;

    @ApiModelProperty(value = "时间划分单位(day-天，hour-小时，min-分钟)", name = "type", required = true)
    private String type;

    @Time
    @ApiModelProperty(value = "开始时间(格式：yyyy-MM-dd HH:mm:ss)", name = "startTime", required = false)
    private String startTime;

    @Time
    @ApiModelProperty(value = "结束时间(格式：yyyy-MM-dd HH:mm:ss)", name = "endTime", required = false)
    private String endTime;
    
    @ApiModelProperty(value = "imei号", name = "imei", required = true)
    private String imei;
    
    public boolean verifyType() {
        switch (this.getType()) {
            case "day":
            case "min":
            case "hour":
                return true;
            default:
                return false;
        }
    }
}
