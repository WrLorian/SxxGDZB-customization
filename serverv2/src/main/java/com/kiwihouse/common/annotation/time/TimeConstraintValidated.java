package com.kiwihouse.common.annotation.time;

import com.kiwihouse.common.utils.TimeUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yjzn
 * @date 2020-1-3 15:55:51
 */
public class TimeConstraintValidated implements ConstraintValidator<Time, String> {

   public boolean isValid(String time, ConstraintValidatorContext context) {

      if(time==null || time.equals("")){
         return true;
      }else{
         return TimeUtil.isValidTime(time);
      }
   }
}
