//package com.example.demo.service;
//
//import com.example.demo.JacksonView;
//import com.example.demo.dao.entity.Role;
//import com.example.demo.dao.entity.User;
//import com.example.demo.dao.repository.UserRepository;
//import com.example.demo.dto.RoleDto;
//import com.example.demo.dto.UserDto;
//import com.example.demo.mapper.PersonMapper;
//import com.example.demo.mapper.UserMapper;
//import com.fasterxml.jackson.annotation.JsonView;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//    private final UserRepository userRepository;
//    private final PersonMapper personMapper;
//    private final UserMapper userMapper;
//
//    public List<UserDto>getUsers(){
//       return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
//    }
//    private UserDto toDto(User user){
//        return UserDto.builder().id(user.getId()).userName(user.getUserName()).roles(user.getRoles().stream().
//                map(this::toRoleDto).toList()).build();
//    }
//    private RoleDto toRoleDto(Role role){
//        return RoleDto.builder().id(role.getId()).roleName(role.getRoleName()).build();
//    }
//
//    public List<User>getUsers1(){
//        return userRepository.findAll();
//    }
//    public List<UserDto>users(){
//        return userMapper.toDtoList(userRepository.users());
//    }
//    public List<UserDto>users1(String name){
//        return userMapper.toDtoList(userRepository.users1(name));
//    }
//    public List<Object[]>users2(String name){
//        return userRepository.users2(name);
//    }
//    public void addUser(String name){
//        userRepository.addUser(name);
//    }
//    public void updateUser(String name,Integer id){
//        userRepository.updateUser(name,id);
//    }
//    public void deleteUser(Long id){
//        userRepository.deleteUser(id);
//    }
//}
