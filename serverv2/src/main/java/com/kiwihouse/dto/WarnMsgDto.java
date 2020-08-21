package com.kiwihouse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yjzn
 * @date 2020-02-26-上午 10:48
 */
@ToString
@Getter
@Setter
public class WarnMsgDto {


    private String cur;
    private String leak;
    private String vol;
    private String temp;
    private String overload;

}
