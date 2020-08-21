package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-02-28-下午 3:51
 */
@ToString
@Getter
@Setter
public class FireEqptAnalyzeDto {

    private String alarmMsg;
    private String addTime;
    private String eqptSn;
    private String siteId;
    private String adminId;
}
