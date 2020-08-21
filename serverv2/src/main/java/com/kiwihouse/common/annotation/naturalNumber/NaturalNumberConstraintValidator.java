package com.kiwihouse.common.annotation.naturalNumber;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author yjzn
 * @date 2019-11-21-上午 10:30
 */
public class NaturalNumberConstraintValidator implements ConstraintValidator<NaturalNumber, String> {

   public boolean isValid(String value, ConstraintValidatorContext context) {

      if(StringUtils.isBlank(value)){
         return true;
      }else{
         try {
            if(Integer.parseInt(value)<0){
               return false;
            }
         }catch (NumberFormatException e){
            return false;
         }
         return true;
      }
   }
}
