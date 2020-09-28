package com.kiwihouse.dao.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ThreeMsgInfo {
	private String [] line;
	private String [] value;
	private String [] status;
//	@Override
//	public String toString() {
//		return "{\"line\":"+Arrays.toString(line)+",\"value\":"+Arrays.toString(value)+",\"status\":"+Arrays.toString(status)+"}";
//		
//	}
	
	
}
