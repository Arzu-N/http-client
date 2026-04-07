package com.example.demo.validation;

import com.example.demo.annotation.NotAdmin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AdminValidation implements ConstraintValidator<NotAdmin,String>{

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null) return true;
        return !s.equals("admin");
    }
}
