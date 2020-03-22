package com.endava.internship.s2020.springcoretask.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameSizeValidator implements ConstraintValidator<NameSize, String> {
    private int min;
    private int max;
    private String message;
    @Override
    public void initialize(NameSize constraintAnnotation) {
        min = constraintAnnotation.min();
        max = constraintAnnotation.max();
        message = constraintAnnotation.message();

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null){
            return false;
        }
        return value.toCharArray().length>2 && value.toCharArray().length<12;
}
}
