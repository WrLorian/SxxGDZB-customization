package com.kiwihouse.vo.kiwihouse;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xin
 * @date 2020/4/30
 */
@Data
public class ThreePhaseVo {

    @ApiModelProperty(value = "Imei",name = "imei")
    private String Imei;


    private String BeginTime;


    private String EndTime;


    private String DataType;

    public boolean verifyDataType() {
        switch (this.getDataType()) {
            case "day":
            case "min":
            case "hour":
                return true;
            default:
                return false;
        }
    }
}
