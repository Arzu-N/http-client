package com.example.demo.annotation;

import com.example.demo.validation.NotOddValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy={NotOddValidator.class})
@Target({FIELD,METHOD,PARAMETER,TYPE_USE,CONSTRUCTOR,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface NotOdd {

    String message() default "{The number cannot be odd}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
