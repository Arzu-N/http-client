package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.example.demo.dao.entity.Car}
 */
@Value
public class CarDto1 implements Serializable {
    @Size(message = "Character limit exceeded", min = 5, max = 15)
    @NotBlank(message = "Marka cannot be blank")
    String marka;
    @NotBlank(message = "Model cannot be blank")
    String model;
    @NotNull
    @PositiveOrZero(message = "Price must be positive or zero")
    BigDecimal price;
    @NotNull
    @Positive
    Integer age;
}