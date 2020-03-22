package com.endava.internship.s2020.springcoretask.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class NotFutureValidator implements ConstraintValidator<NotFuture, LocalDate>    {
    @Override
    public void initialize(NotFuture constraintAnnotation) {}

    @Override
    public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
        if(date==null){
            return false;
        }
        return date.compareTo(LocalDate.now())<0;
    }
}
