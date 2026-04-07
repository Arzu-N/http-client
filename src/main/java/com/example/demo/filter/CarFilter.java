package com.example.demo.filter;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarFilter {
    @NotBlank(message = "This field" +
            " cannot be empty")
    private String marka;

    @NotBlank(message = "This field cannot be empty")
    private String model;

    @NotNull(message = "This field cannot be empty")
    @DecimalMin(value = "0", message = "This field must be greater than 0")
    private BigDecimal minPrice;

    @NotNull(message = "This field cannot be empty")
    private BigDecimal maxPrice;

    @NotNull(message = "This field cannot be empty")
    @Min(value = 0, message = "This filed must be greater than 0")
    private Integer minAge;

    @NotNull(message = "This filed cannot be empty")
    private Integer maxAge;
}
