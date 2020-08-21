package com.kiwihouse.common.annotation.date;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yjzn
 * @date 2020-1-3 15:55:42
 */
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateConstraintValidated.class)
public @interface Date {

    String message() default "日期格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
