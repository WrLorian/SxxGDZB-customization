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
public class AlarmMsg {

    private String csq;
    private String cur;
    private String vol;
    private String pwr;
    private String kwh;
    private String lime_temp;
    private String pwr_fct;
    private String leak_cur;
}
