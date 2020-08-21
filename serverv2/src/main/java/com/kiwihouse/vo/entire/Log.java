package com.kiwihouse.vo.entire;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author yjzn
 * @date 2019年12月19日15:37:31
 */
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class Log {
    private String ip;
    private String msg;
    private Object param;

}
