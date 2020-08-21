package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 查询设备信息时，查询对应的告警信息
 * @author yjzn
 * @date 2020-1-7 18:16:50
 */
@ToString
@Getter
@Setter
public class AlmIdMsgDto {

    private String alarmId;
    private String alarmMsg;
}
