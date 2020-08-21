package com.kiwihouse.common.annotation.naturalNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yjzn
 * @date 2019-11-21-上午 10:29
 */
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NaturalNumberConstraintValidator.class)
@Documented
public @interface NaturalNumber {

    String message() default "必须为数字类型";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
