package com.example.demo.dao.entity;

import com.example.demo.JacksonView;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Role {

    @JsonView(JacksonView.Detailed.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView(JacksonView.Detailed.class)
    @JsonProperty(value = "ROLE NAME")
    private String roleName;


    @JsonView(JacksonView.Detailed.class)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user_id")
    User user;

}
