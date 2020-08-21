package com.kiwihouse.common.annotation.doubleNumber;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yjzn
 * @date 2019-11-21-上午 10:30
 */
public class NumberConstraintValidator implements ConstraintValidator<DoubleNumber, String> {
   public void initialize(DoubleNumber constraint) {
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {

      if(StringUtils.isBlank(value)){
         return true;
      }else{
         try {
            Double valueOf = Double.valueOf(value);
            if(valueOf<0){
               return false;
            }
         }catch (NumberFormatException e){
            return false;
         }
         return true;
      }
   }
}
