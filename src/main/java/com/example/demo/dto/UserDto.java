package com.example.demo.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter@Builder
public class UserDto {
    private Long id;
    private String userName;
    private List<RoleDto> roles;
}
