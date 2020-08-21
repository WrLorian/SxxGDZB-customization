package com.kiwihouse.common.annotation.doubleNumber;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yjzn
 * @date 2019-11-21-上午 10:29
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NumberConstraintValidator.class)
@Documented
public @interface DoubleNumber {

    String message() default "必须为double类型";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
