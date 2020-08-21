package com.kiwihouse.vo.onenet;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2020-1-8 16:21:14
 */
@Accessors(chain = true)
@ToString
@Getter
@Setter
public class OneNetResult {

    private String errno;
    private String error;
    private String data;
}
