package com.kiwihouse.vo.entire;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @author yjzn
 * @date 2019年12月19日15:37:31
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "返回数据")
public class ResultList {

    @ApiModelProperty(value = "响应码",name = "code")
    private int code;
    @ApiModelProperty(value = "提示消息",name = "msg")
    private String msg;
    private Result result;

}
