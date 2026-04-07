package com.example.demo.dto;

import com.example.demo.annotation.Register;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Register
public class RegisterDto {
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
    private String email;
}
