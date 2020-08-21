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
@ApiModel(description = "响应体")
public class Result<T> {

    @ApiModelProperty(value = "返回数据行数",name = "row")
    private Integer row;
    @ApiModelProperty(value = "具体返回数据",name = "data")
    private T data;

}
