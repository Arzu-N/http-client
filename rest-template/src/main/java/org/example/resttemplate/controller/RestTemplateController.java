package org.example.resttemplate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.service.RestTemplateService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/rest-template/api/v1")
@RequiredArgsConstructor
public class RestTemplateController {

    private final RestTemplateService restTemplateService;

    @GetMapping("/get/{id}")
    public ResponseEntity<CarResponseDto>getCarById(@PathVariable Long id){
    return ResponseEntity.ok(restTemplateService.getCarById(id));
}

@GetMapping("/get-detail/{id}")
    public ResponseEntity<?>getCarDetail(@PathVariable Long id){
        return restTemplateService.getCarDetail(id);
}

@GetMapping("/by-model")
    public ResponseEntity<?>getCarByModel(@RequestParam String model){
        return restTemplateService.getCarByModel(model);
}

@GetMapping("/get1/{id}")
    public  ResponseEntity<?>getCarById1(@PathVariable Long id, ServletWebRequest servletRequest){
        return restTemplateService.getCarById1(id);
}


@GetMapping("by-model1")
    public ResponseEntity<?>getCarByModel1(@RequestParam String model){
        return restTemplateService.getCarByModel1(model);
}

@GetMapping("/sum/{value1}/{value2}")
    public int sum(@PathVariable int value1,@PathVariable int value2){
        return restTemplateService.sum(value1,value2);
}

@GetMapping("/sum1")
    public int sum1(@RequestParam int value1,@RequestParam int value2,
                    @RequestParam int value3){
        return restTemplateService.sum1(value1,value2,value3);
}
@GetMapping("/sum2/{value1}/{value2}")
public int sum2(@PathVariable int value1,@PathVariable int value2){
        return restTemplateService.sum2(value1,value2);
}

@GetMapping("/sum3")
    public int sum3(@RequestParam int value1,@RequestParam int value2,@RequestParam int value3){
        return restTemplateService.sum3(value1,value2,value3);
}

@GetMapping("/get-by-id/{id}")
    public ResponseEntity<CarResponseDto>getCarById2(@PathVariable Long id){
        return ResponseEntity.ok(restTemplateService.getCarById2(id));
}

@GetMapping("/get-by-id1/{id}")
    public ResponseEntity<CarResponseDto>getCarById3(@PathVariable Long id){
        return ResponseEntity.ok(restTemplateService.getCarById3(id));
}

@GetMapping("/by-model2")
    public ResponseEntity<CarResponseDto>getCarByModel2(@RequestParam String model){
    CarResponseDto carByModel2 = restTemplateService.getCarByModel2(model);
    return ResponseEntity.ok(carByModel2);
}

@GetMapping("/by-model3")
    public ResponseEntity<CarResponseDto>getCarByModel3(@RequestParam String model){
        return ResponseEntity.ok(restTemplateService.getCarByModel3(model));
}

@GetMapping("/repeat/{count}")
    public ResponseEntity<?>repeat(@PathVariable int count,@RequestParam String word){
        return restTemplateService.repeat(count,word);
}

@GetMapping("/get-by-id2/{id}")
    public ResponseEntity<?>getById2(@PathVariable Long id)throws JsonProcessingException {
    return restTemplateService.getCarById4(id);
}

@GetMapping("/get")
    public ResponseEntity<List<CarResponseDto>>getCars(@RequestHeader HttpHeaders headers){
headers.forEach((key,value)-> System.out.println("header: "+key+"=>"+value));

        return restTemplateService.getCars();
}

@PostMapping("/post")
    public ResponseEntity<?>addCar( @RequestBody CarDto1 dto) throws JsonProcessingException {
        return restTemplateService.addCar(dto);

}

@PostMapping("/post1")
    public ResponseEntity<?>addCar1(@RequestBody CarDto1 dto)throws JsonProcessingException{
        return restTemplateService.addCar1(dto);
}

@PostMapping("/post2")
    public ResponseEntity<?>addCar2(@RequestBody CarDto1 dto) throws JsonProcessingException {
        return restTemplateService.addCar2(dto);
}

@PostMapping("/post3/{model}")
    public ResponseEntity<?>addCar3(@PathVariable String model, @RequestParam BigDecimal price,
                                    @RequestBody CarDto1 dto) throws JsonProcessingException {
        return restTemplateService.addCar3(model,price,dto);
}

@DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteCar(@PathVariable Long id) throws JsonProcessingException {
    return restTemplateService.deleteCar(id);
}

@DeleteMapping("/delete1/{id}")
    public ResponseEntity<?>deleteCar1(@PathVariable Long id)throws JsonProcessingException{
        return restTemplateService.deleteCar1(id);
}

@PutMapping("/update/{id}")
    public ResponseEntity<?>updateCar(@PathVariable Long id,@RequestBody CarDto1 dto) throws JsonProcessingException {
        return restTemplateService.updateCar(id,dto);
}



@PutMapping("/update1/{id}")
public ResponseEntity<?> updateCAr1(@PathVariable Long id,@RequestBody CarDto1 dto1) throws JsonProcessingException {
        return restTemplateService.updateCar1(id,dto1);

}
}
