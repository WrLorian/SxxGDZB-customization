package com.kiwihouse.common.annotation.imei;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * imei必须为15位正数字
 * @author cwp
 * @data 2020-1-3 15:56:29
 */
public class ImeiConstraintValidator implements ConstraintValidator<Imei, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        Pattern pattern = Pattern.compile("^[+]?[\\d]*$"); //判断imei是否全为正数字
        if(null==value || value.equals("")){
            return true;
        }else if(value.length()!=15){
            return false;
        }else {
            return pattern.matcher(value).matches();
        }

    }
}

