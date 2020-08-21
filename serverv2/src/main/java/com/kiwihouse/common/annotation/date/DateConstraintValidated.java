package com.kiwihouse.common.annotation.date;


import com.kiwihouse.common.utils.TimeUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yjzn
 * @date 2020-1-3 15:55:51
 */
public class DateConstraintValidated implements ConstraintValidator<Date, String> {

   public boolean isValid(String date, ConstraintValidatorContext context) {

      if(date==null || date.equals("")){
         return true;
      }else{
         return TimeUtil.isValidDate(date);
      }
   }
}
