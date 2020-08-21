package com.kiwihouse.vo.onenet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-1-8 20:48:14
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class DeviceQuery {

    private int code;
    private String online;
}
