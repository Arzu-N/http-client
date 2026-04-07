package com.example.demo.dao.entity;

import com.example.demo.JacksonView;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "usersss")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @JsonView({JacksonView.Simple.class,JacksonView.Basic.class,JacksonView.Detailed.class})
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonView({JacksonView.Basic.class,JacksonView.Detailed.class})
    private String userName;

    @JsonView(JacksonView.Detailed.class)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user_id")
    List<Role> roles;
}
