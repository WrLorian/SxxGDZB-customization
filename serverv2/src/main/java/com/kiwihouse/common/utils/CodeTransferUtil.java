package com.kiwihouse.common.utils;

import com.kiwihouse.common.bean.Code;
import com.kiwihouse.common.customException.CodeTransferException;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 陈伟平
 * @date 2020-03-27-下午 4:12
 */
public class CodeTransferUtil {

    private static final String CODE = "code";              //区域行政编码属性名
    private static final String PROVINCE = "province";      //省属性名
    private static final String CITY = "city";              //市属性名
    private static final String DISTRICT = "district";      //区属性名


    public static void transferOne(String code,Object object){
        if (StringUtils.isNotBlank(code)) {
            CodeTransferUtil.transferOne(object);
        }
    }
    public static void transferAll(String code,Object object){
        if (StringUtils.isNotBlank(code)) {
            CodeTransferUtil.transferAll(object);
        }
    }

    /**
     * 将区域行政编码解析为对应的省市区之一
     * @param object
     */
    private static void transferOne(Object object){

        try {

            Class<?> clazz = object.getClass();
            Field codeFiled = clazz.getDeclaredField(CODE);
            codeFiled.setAccessible(true);
            String code = (String) codeFiled.get(object);

            ConcurrentHashMap<String, String> codeMapProvince = new FileThread().getCodeMap("file/china-Province.txt");
            ConcurrentHashMap<String, String> codeMapCity = new FileThread().getCodeMap("file/china-City.txt");
            ConcurrentHashMap<String, String> codeMapCounty = new FileThread().getCodeMap("file/china-County.txt");

            String province = codeMapProvince.get(code);
            String city = codeMapCity.get(code);
            String district = codeMapCounty.get(code);

            if(null==province&&null==city&&null==district){
                throw new CodeTransferException(Code.PARAM_FORMAT_ERROR.getCode(),"code`"+code+"`不存在");
            }

            Field provinceField = clazz.getDeclaredField(PROVINCE);
            provinceField.setAccessible(true);
            provinceField.set(object,province);

            Field cityField = clazz.getDeclaredField(CITY);
            cityField.setAccessible(true);
            cityField.set(object,city);

            Field districtField = clazz.getDeclaredField(DISTRICT);
            districtField.setAccessible(true);
            districtField.set(object,district);

        } catch (Exception e) {
            throw new CodeTransferException(Code.EXECUTION_ERROR.getCode(),"解析行政编码失败不存在");
        }

    }


    /**
     * 将区域行政编码解析为对应的省市区
     * @param object
     */
    private static void transferAll(Object object){

        try {

            Class<?> clazz = object.getClass();
            Field codeFiled = clazz.getDeclaredField(CODE);
            codeFiled.setAccessible(true);
            String code = (String) codeFiled.get(object);

            ConcurrentHashMap<String, String> codeMapProvince = new FileThread().getCodeMap("file/Province.txt");
            ConcurrentHashMap<String, String> codeMapCity = new FileThread().getCodeMap("file/City.txt");
            ConcurrentHashMap<String, String> codeMapCounty = new FileThread().getCodeMap("file/china-County.txt");

            String province = codeMapProvince.get(code.substring(0,2));
            String city = codeMapCity.get(code.substring(0,4));
            String district = codeMapCounty.get(code);

            if(null==province&&null==city&&null==district){
                throw new CodeTransferException(Code.PARAM_FORMAT_ERROR.getCode(),"code`"+code+"`不存在");
            }

            Field provinceField = clazz.getDeclaredField(PROVINCE);
            provinceField.setAccessible(true);
            provinceField.set(object,province);

            Field cityField = clazz.getDeclaredField(CITY);
            cityField.setAccessible(true);
            cityField.set(object,city);

            Field districtField = clazz.getDeclaredField(DISTRICT);
            districtField.setAccessible(true);
            districtField.set(object,district);

        } catch (Exception e) {
            throw new CodeTransferException(Code.EXECUTION_ERROR.getCode(),"解析行政编码失败不存在");
        }
    }

}
