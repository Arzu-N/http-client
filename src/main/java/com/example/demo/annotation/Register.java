package com.example.demo.annotation;

import com.example.demo.validation.RegisterValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = {RegisterValidator.class})
public @interface Register {

    String message() default "{Password and confirmPassword do not match}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
