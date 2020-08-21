package com.kiwihouse.common.annotation.imei;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


/**
 * IMEI约束,当IMEI不为空时校验IMEI号
 * 1.必须为15位数字
 * @author yjzn
 * @date 2020-1-3 15:55:57
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ImeiConstraintValidator.class)
@Documented
public @interface Imei {

    String message() default "IMEI长度不正确（15位）";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

