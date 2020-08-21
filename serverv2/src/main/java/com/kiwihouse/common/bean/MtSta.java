package com.kiwihouse.common.bean;

import lombok.Getter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-1-8 11:07:28
 */
@ToString
@Getter
public enum MtSta {

    UNPROCESSED("0"),
    PROCESSED("1"),
    CANCEL("2");

    private final String code;

    MtSta(String code) {
        this.code = code;
    }

}
