package org.example.resttemplate.controller;


import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.service.WebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/web-client/api/v1")
public class WebClientController {

    private final WebClientService webClientService;

    @GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>>getCars(){
        return webClientService.getCars();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CarResponseDto>getCarById(@PathVariable Long id){
        return webClientService.getCarById(id);
    }

    @GetMapping("/get/by-model")
    public ResponseEntity<CarResponseDto>getCarByModel(@RequestParam String model){
        return webClientService.getCarByModel(model);
    }

    @PostMapping("/add")
    public ResponseEntity<CarResponseDto>addCar(@RequestBody CarDto1 dto){
        return webClientService.addCar(dto);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<CarResponseDto>updateCar(@PathVariable Long id,@RequestBody CarDto1 dto){
        return webClientService.updateCar(id,dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id, ServletWebRequest servletRequest){
        return webClientService.deleteCar(id);
    }
}
