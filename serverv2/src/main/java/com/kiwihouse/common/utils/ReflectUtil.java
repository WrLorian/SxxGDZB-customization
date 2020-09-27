package com.kiwihouse.common.utils;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yjzn
 * @date 2020-1-6 14:58:07
 */
@Component
public class ReflectUtil {

    /**
     * 返回属性值为非空的，名称和植
     * @param object 属性为字符创的对象
     */
    public static ArrayList<HashMap<String,String>> GetNoneEmptyField(Object object){
        ArrayList<HashMap<String,String>> list = new ArrayList<>();

        String[] fieldNames = getFiledName(object);

        //获取属性的名字
        for (String name : fieldNames) {
            String value = (String) getFieldValueByName(name, object);    //属性值

            if (null != value) {
                HashMap<String,String> map = new HashMap<>();
                map.put(name, value);
                list.add(map);
            }
        }

        return list;
    }


    /**
     * 返回属性值为非空的，名称和植
     * @param object 属性为字符创的对象
     */
    public static HashMap<String,Object> GetNoneEmptyFieldMap(Object object){
    	
        HashMap<String,Object> map = new HashMap<>();
        String[] fieldNames = getFiledName(object);
        
        //获取属性的名字
        for (String name : fieldNames) {
            String value = (String) getFieldValueByName(name, object);    //属性值
            System.out.println("------------------->" + value);
            if (null != value && value != "") {
            	if(value.contains(".")) {
            		map.put(name, Float.valueOf(value));
            	}else {
            		map.put(name, Integer.valueOf(value));
            	}
            }
        }

        return map;
    }

    /**
     * @param o 目标对象
     * @return 属性名数组
     */
    private static String[] getFiledName(Object o){
        Field[] fields=o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            fieldNames[i]=fields[i].getName();
        }
        return fieldNames;
    }

    /**
     * 根据属性名获取属性值
     * @param fieldName 属性名
     * @param o 目标对象
     * @return 属性名对应属性值
     */
    public static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            return null;
        }
    }
}
