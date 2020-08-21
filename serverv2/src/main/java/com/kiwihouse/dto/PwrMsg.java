package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author 陈伟平
 * @date 2020-03-30-下午 6:26
 */
@ToString
@Getter
@Setter
public class PwrMsg {

    private int num;
    private List<Double> pwr;

}
