package org.example.resttemplate.controller;

import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.service.FeignService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feign")
public class FeignController {

    private final FeignService feignService;

    @GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>>getCars(){
    return ResponseEntity.ok(feignService.getCars());
}

@GetMapping("/get/{id}")
public ResponseEntity<CarResponseDto>getCarById(@PathVariable Long id){
        return ResponseEntity.ok(feignService.getCarById(id));
}

@GetMapping("/get-by-model")
    public ResponseEntity<CarResponseDto>getCarByModel(@RequestParam String model){
        return  ResponseEntity.ok(feignService.getCarByModel(model));
}

@PostMapping("/add")
public ResponseEntity<CarResponseDto>addCAr(@RequestBody CarDto1 dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(feignService.addCar(dto));
}

@PutMapping("/put/{id}")
    public ResponseEntity<CarResponseDto>updateCar(@PathVariable Long id,@RequestBody CarDto1 dto){
        return ResponseEntity.ok(feignService.updateCar(id,dto));
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id){
        feignService.deleteCar(id);
        return ResponseEntity.noContent().build();
}
}
