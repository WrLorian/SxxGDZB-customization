package com.kiwihouse.controller.common;

import io.swagger.annotations.ApiParam;
import lombok.Data;

/**
 * @author xin
 * @date 2020/7/19
 */
@Data
public class PageParams {
    @ApiParam(name = "分页大小", required = true, defaultValue = "10")
    Integer pageSize;

    @ApiParam(name = "当前页码", required = true, defaultValue = "1")
    Integer page;

}
