package com.kiwihouse.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ThreePhaseMsg {
    private String cur;
    private String leak;
    private String vol;
    private String temp;
    private String overload;
    private Integer totalStatus ; 
//	return "{\"vol\":"+vol+",\"totalStatus\":"+totalStatus+"}";
}
