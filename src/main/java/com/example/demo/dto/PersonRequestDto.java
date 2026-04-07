package com.example.demo.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonRequestDto {
    @NotBlank(message="this field cannot be empty")
    private String userName;
    @NotBlank(message = "This field cannot be empty")
    private String email;
    @Min(value=18,message = "Age must be greater than 18")
    private Integer age;
    @Min(value = 400,message ="Salary must be greater than 400" )
    @Max(value=100000,message = "Salary must be less than 100000")
    private Double salary;
    @NotNull(message="This field cannot be empty")
    private LocalDate birthDay;

}
