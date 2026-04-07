package org.example.resttemplate.controller;

import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.service.ReactiveService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Flushable;
import java.lang.management.MonitorInfo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reactive/api/v1")
public class ReactiveController {

    private final ReactiveService reactiveService;

    @GetMapping("/get")
    public Flux<CarResponseDto>getCars(){
        return reactiveService.getCars();
    }

    @GetMapping("/get/{id}")
    public Mono<CarResponseDto>getCarById(@PathVariable Long id){
        return reactiveService.getCarById(id);
    }

    @GetMapping("/get/by-model")
    public Mono<CarResponseDto>getCarById(@RequestParam String model){
        return reactiveService.getCarByModel(model);
    }

    @PostMapping("/post")
    public Mono<CarResponseDto>addCar(@RequestBody CarDto1 dto){
        return  reactiveService.addCar(dto);
    }

    @PutMapping("/put/{id}")
    public Mono<CarResponseDto>updateCar(@PathVariable Long id,@RequestBody CarDto1 dto){
        return reactiveService.updateCar(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>>deleteCar(@PathVariable Long id){
        return reactiveService.deleteCar(id);
    }
}
