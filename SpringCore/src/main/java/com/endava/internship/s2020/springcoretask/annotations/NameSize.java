package com.endava.internship.s2020.springcoretask.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = {FIELD, METHOD, PARAMETER, ANNOTATION_TYPE, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {NameSizeValidator.class})
public @interface NameSize {
    String message() default "Incorrect size of name!\n";
    int min() default 2;
    int max() default 12;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
