package com.kiwihouse.common.customException;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author 陈伟平
 * @date 2020-03-27-下午 4:47
 */
@ToString
@Getter
@Setter
public class CodeTransferException extends RuntimeException{

    public CodeTransferException(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    private int code;
    private String msg;

}
