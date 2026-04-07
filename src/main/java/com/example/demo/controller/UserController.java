//package com.example.demo.controller;
//
//import com.example.demo.JacksonView;
//import com.example.demo.dao.entity.User;
//import com.example.demo.dto.UserDto;
//import com.example.demo.service.UserService;
//import com.fasterxml.jackson.annotation.JsonView;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/v1/users")
//public class UserController {
//    private final UserService userService;
//
//    @GetMapping
//    ResponseEntity<List<UserDto>>getUsers(){
//        return ResponseEntity.ok(userService.getUsers());
//    }
//    @JsonView(JacksonView.Simple.class)
//    @GetMapping("/yalnis")
//    public ResponseEntity<List<User>>getUsers1(){
//        return ResponseEntity.ok(userService.getUsers1());
//    }
//    @JsonView(JacksonView.Detailed.class)
//    @GetMapping("/detailed")
//    public ResponseEntity<List<User>>getUsers2(){
//        return ResponseEntity.ok(userService.getUsers1());
//    }
//@GetMapping("/pg")
//    public ResponseEntity<List<UserDto>>users(){
//        return ResponseEntity.ok(userService.users());
//}
//@GetMapping("/pg1")
//    public ResponseEntity<List<UserDto>>users1(@RequestParam String name){
//        return ResponseEntity.ok(userService.users1(name));
//}
//@GetMapping("/pg2")
//    public ResponseEntity<List<Object[]>>users2(@RequestParam String name){
//        return ResponseEntity.ok(userService.users2(name));
//}
//@PostMapping("/pg3")
//    public ResponseEntity<Void>addUser(@RequestParam String name){
//        userService.addUser(name);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//}
//@PutMapping("/pg4/{id}")
//    public ResponseEntity<Void> updateUser(@RequestParam String name,@PathVariable Integer id){
//        userService.updateUser(name,id);
//        return ResponseEntity.ok().build();
//}
//@DeleteMapping("/{id}")
//    public ResponseEntity<Void>deleteUser(@PathVariable Long id){
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//}
//}
