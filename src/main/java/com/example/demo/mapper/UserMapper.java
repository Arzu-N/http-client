package com.example.demo.mapper;

import com.example.demo.dao.entity.User;
import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRequestDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper
{
//    User toEntity(UserRequestDto dto);
//    UserDto toDto(User user);
//    List<UserDto> toDtoList(List<User>list);
}
