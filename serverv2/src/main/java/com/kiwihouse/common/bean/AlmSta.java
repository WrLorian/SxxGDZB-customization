package com.kiwihouse.common.bean;

import lombok.Getter;
import lombok.ToString;

/**
 * 订单状态:
 *      -1：错误告警
 *      0:未处理
 *      1:已转为工单
 *      2:已处理
 *      99:为添加到告警信息，直接转为工单
 * @author yjzn
 * @date 2020-1-8 10:31:42
 */
@ToString
@Getter
public enum AlmSta {

    ERROR(-1),
    UNPROCESSED(0),
    TO_ORDER(1),
    PROCESSED(2);

    //枚举的属性字段必须是私有且不可变
    private final int code;

    AlmSta(int code) {
        this.code=code;
    }
}
