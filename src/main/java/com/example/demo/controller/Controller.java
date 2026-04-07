package com.example.demo.controller;

import com.example.demo.exception.CarNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo/api/v1")
public class Controller {

    @GetMapping("/sum/{value1}/{value2}")
    public int sum(@PathVariable int value1,@PathVariable int value2){
        return value1+value2;
    }

    @GetMapping("/sum1")
    public int sum1(@RequestParam int value1,@RequestParam int value2,
                    @RequestParam int value3){
        return value1+value2+value3;
    }

    @GetMapping("/repeat/{count}")
    public ResponseEntity<String>repeat(@PathVariable int count,@RequestParam String word){
        if(word.equals("a"))
            throw new IllegalArgumentException("Illegal Argument");
        if(word.equals("b"))
            throw new CarNotFoundException("car not found");
        String repeat = word.repeat(count);
        return ResponseEntity.ok(repeat);
    }
}
