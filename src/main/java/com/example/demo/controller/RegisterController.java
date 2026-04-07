package com.example.demo.controller;

import com.example.demo.dto.RegisterDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class RegisterController {
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto dto){
        return ResponseEntity.ok("Successfully register");
    }
}
