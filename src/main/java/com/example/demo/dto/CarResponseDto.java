package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto {
    private Long
            id;
    private String marka;

    private String model;

    private BigDecimal price;

    private Integer age;
}
