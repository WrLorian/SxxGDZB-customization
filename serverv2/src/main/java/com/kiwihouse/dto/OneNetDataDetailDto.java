package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yjzn
 * @date 2020-01-03-上午 9:53
 */
@Getter
@Setter
public class OneNetDataDetailDto {

    private int res_id;
    private String val;

    @Override
    public String toString() {
        return "{" +
                "\"res_id\":" + res_id +
                ", \"val\":\"" + val + '\"' +
                '}';
    }
}
