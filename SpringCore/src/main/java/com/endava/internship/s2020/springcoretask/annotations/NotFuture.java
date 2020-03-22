package com.endava.internship.s2020.springcoretask.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NotFutureValidator.class})
public @interface NotFuture {
    String message() default "Year should be in Past!\n";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}