package com.example.demo.validation;

import com.example.demo.annotation.NotOdd;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class NotOddValidator implements ConstraintValidator<NotOdd, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal bigDecimal, ConstraintValidatorContext
            constraintValidatorContext) {
       if(bigDecimal==null) return true;
       return bigDecimal.remainder(BigDecimal.valueOf(2)).compareTo(BigDecimal.ZERO)==0;
    }
}
