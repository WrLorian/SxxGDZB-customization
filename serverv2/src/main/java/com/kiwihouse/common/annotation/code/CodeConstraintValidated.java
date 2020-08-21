package com.kiwihouse.common.annotation.code;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 当区域编码(code)不为空时,校验code
 * @author yjzn
 * @date 2019-12-30 11:22:32
 */
public class CodeConstraintValidated implements ConstraintValidator<Code, String> {

   public boolean isValid(String code, ConstraintValidatorContext context) {

      if(StringUtils.isBlank(code)){
         return true;
      }else{
         if(code.length()!=6){
            return false;
         }else {
            try {
               Integer codeInt = Integer.valueOf(code);
               if(codeInt<100000 || codeInt>999999){
                  return false;
               }
            }catch (NumberFormatException e){
               return false;
            }
         }
      }
      return true;
   }
}
