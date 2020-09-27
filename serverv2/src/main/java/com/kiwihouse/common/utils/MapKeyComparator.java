package com.kiwihouse.common.utils;

import java.util.Comparator;
import java.util.Date;

public class MapKeyComparator implements Comparator<String>{
	@Override

    public int compare(String str1, String str2) {
		Date time1= TimeUtil.strToDate(str1);
		Date time2=  TimeUtil.strToDate(str2);
		if(time1.getTime() > time2.getTime()) {
			return 1;
		}else {
			return -1;
		}
    }
	
	
	public static void main(String[] args) {
		String a = "b";
		String b = "a";
		System.out.println("--------》" + a.compareTo(b));
	}
}
