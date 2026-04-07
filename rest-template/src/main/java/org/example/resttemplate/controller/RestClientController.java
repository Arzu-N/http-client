package org.example.resttemplate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.service.RestClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.smartcardio.Card;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest-client/api/v1")
public class RestClientController {

    private final RestClientService restClientService;

    @GetMapping("/{id}")
    public ResponseEntity<CarResponseDto>getCarById(@PathVariable Long id){
        ResponseEntity<CarResponseDto> carById = restClientService.getCarById(id);
        return carById;
    }

    @GetMapping("/body/{id}")
    public ResponseEntity<?> getCarById1(@PathVariable Long id) throws JsonProcessingException {
        return restClientService.getCarById1(id);
    }

    @GetMapping("/void/{id}")
    public ResponseEntity<Void>getCarById2(@PathVariable Long id){
        restClientService.getCarById2(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>>getCars(){
        return restClientService.getCars();
    }

    @PostMapping("/add")
    public ResponseEntity<CarResponseDto>addCar(@RequestBody CarDto1 dto){
        return restClientService.addCar(dto);
    }

    @PostMapping("/add1")
    public ResponseEntity<CarResponseDto>addCar1(@RequestBody CarDto1 dto){
        return restClientService.addCar(dto);
    }

    @PostMapping("/add2")
    public ResponseEntity<Void>addCar2(@RequestBody CarDto1 dto){
        restClientService.addCar2(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponseDto>updateCar(@PathVariable Long id, @RequestBody CarDto1 dto){
        return restClientService.updateCar(id,dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deleteCar(@PathVariable Long id){
        return restClientService.deleteCar(id);
    }

    @GetMapping("/by-model")
    public ResponseEntity<?>getCarByModel(@RequestParam String model){
        return restClientService.getCarByModel(model);
    }
}
