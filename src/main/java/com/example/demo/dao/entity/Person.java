package com.example.demo.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(nullable=true)
    private String email;
    private Integer age;
    private Double salary;
    private LocalDate birthDay;

}
