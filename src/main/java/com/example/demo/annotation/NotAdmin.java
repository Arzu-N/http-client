package com.example.demo.annotation;

import com.example.demo.validation.AdminValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy={AdminValidation.class})
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER,ElementType.TYPE,
        ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE})

public @interface NotAdmin {
    String message() default "Not admin";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
