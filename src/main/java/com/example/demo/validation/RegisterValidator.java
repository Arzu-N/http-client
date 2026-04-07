package com.example.demo.validation;

import com.example.demo.annotation.Register;
import com.example.demo.dto.RegisterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegisterValidator implements ConstraintValidator<Register, RegisterDto> {
    @Override
    public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext constraintValidatorContext) {
        if(registerDto.getPassword()==null||registerDto.getConfirmPassword()==null)
            return  true;

        return registerDto.getPassword().equals(registerDto.getConfirmPassword());

/*        if(registerDto.getPassword()==null||registerDto.getConfirmPassword()==null)
            return  true;


constraintValidatorContext.disableDefaultConstraintViolation();
constraintValidatorContext.buildConstraintViolationWithTemplate
        ("password and confirmPassword do not match")
        .addPropertyNode("confirmPassword")
        .addConstraintViolation();
        return registerDto.getPassword().equals(registerDto.getConfirmPassword());
    }*/
}
}