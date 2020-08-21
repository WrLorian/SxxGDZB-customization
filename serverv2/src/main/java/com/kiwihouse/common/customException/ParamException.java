package com.kiwihouse.common.customException;

import com.kiwihouse.common.bean.Code;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 陈伟平
 * @date 2020-04-02-下午 3:30
 */
@ToString
@Getter
@Setter
public class ParamException extends RuntimeException {

    public ParamException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ParamException(Code code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }

    private int code;
    private String msg;
}
