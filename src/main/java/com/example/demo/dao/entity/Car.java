package com.example.demo.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "car")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Car extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "marka", nullable = false, length = Integer.MAX_VALUE)
    private String marka;

    @Column(name = "model", nullable = false, length = Integer.MAX_VALUE)
    private String model;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "age", nullable = false)
    private Integer age;


}