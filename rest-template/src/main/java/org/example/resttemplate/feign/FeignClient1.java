package org.example.resttemplate.feign;

import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="car",url ="${car.url}")
public interface FeignClient1 {

    @GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>> getCars();

    @GetMapping("/get-{id}")
    public ResponseEntity<CarResponseDto>getCarById(@PathVariable Long id);

    @GetMapping("/by-model")
    public ResponseEntity<CarResponseDto>getCarByModel(@RequestParam String model);


    @PostMapping("/post1")
    public ResponseEntity<CarResponseDto>addCar(@RequestBody CarDto1 dto);

    @PutMapping("/put-{id}")
    public ResponseEntity<CarResponseDto>updateCar(@PathVariable Long id,@RequestBody CarDto1 dto);

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id);

}
