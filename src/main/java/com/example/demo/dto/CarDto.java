package com.example.demo.dto;

import com.example.demo.annotation.NotAdmin;
import com.example.demo.annotation.NotOdd;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @Size(min = 3, max = 15, message = "Size must be greater than 5 and less than 15")
    @NotBlank(message = "Marka cannot be empty")
    @NotAdmin
    private String marka;

    @NotBlank(message = "Model cannot be empty")
    private String model;

    @NotNull(message = "Age cannot be null")
    @Positive(message = "Age must be positive")
    private Integer age;

    @NotNull(message = "Price cannot be empty")
    @Min(value = 100, message = "Price must be greater than 5")
    @Max(value = 200, message = "Price must be less than 15")
    @NotOdd
    private BigDecimal price;

/*    @NotEmpty(message = "List cannot be empty")
    private List<String> people;*/

    @Future(message = "CreatedDate must be in the future")
    private LocalDateTime createdDate;

    @Past(message = "Birthday must be in the past")
    private LocalDateTime birthDay;

 /*   @NotBlank(message = "Email cannot be empty")
    @Email
    private String email;*/
}
