package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yjzn
 * @date 2020-01-03-上午 9:45
 */
@Getter
@Setter
public class OneNetDataDto {

    private List<OneNetDataDetailDto> data;

    @Override
    public String toString() {
        return "{" +
                "\"data\":" + data +
                '}';
    }
}
