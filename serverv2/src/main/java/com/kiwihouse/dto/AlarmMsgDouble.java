package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-1-16 15:36:36
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class AlarmMsgDouble {

    private Double cur;
    private Double vol;
    private Double pwr;
    private Double kwh;
    private Double line_temp;
    private Double pwr_fct;
    private Double leak_cur;
}
