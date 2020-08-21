package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-2-25 16:01:02
 */
@ToString
@Getter
@Setter
public class MeterNowDto {

    private String eqptSn;
    private String alarmId;
    private String alarmMsg;
    private String groupId;
    private String adminId;
    private String times;

    private String kwh;
    private String addTime;
}
