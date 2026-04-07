package org.example.resttemplate.service;

import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WebClientService {
    private final WebClient webClient;

public ResponseEntity<List<CarResponseDto>>getCars(){
    List<CarResponseDto> block = webClient.get()
            .uri("/get")
            .retrieve()
            .bodyToFlux(CarResponseDto.class)
            .collectList()
            .block();
    return ResponseEntity.ok(block);

}

public ResponseEntity<CarResponseDto>getCarById(Long id){
    CarResponseDto block = webClient.get()
            .uri("/get-{id}",id)
            .retrieve()
            .bodyToMono(CarResponseDto.class)
            .block();
    return ResponseEntity.ok(block);
}

public ResponseEntity<CarResponseDto>getCarByModel(String model){
    CarResponseDto block = webClient.get()
            .uri("/by-model?model={model}", model)
            .retrieve()
            .bodyToMono(CarResponseDto.class)
            .block();
    return ResponseEntity.ok(block);}

    public ResponseEntity<CarResponseDto>addCar(CarDto1 dto){
        CarResponseDto block = webClient.post()
                .uri("/post1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();
        return ResponseEntity.status(HttpStatus.CREATED).body(block);
    }

    public ResponseEntity<CarResponseDto>updateCar(Long id,CarDto1 dto){
        CarResponseDto block = webClient.put()
                .uri("/put-{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(CarResponseDto.class)
                .block();
        return ResponseEntity.ok(block);
    }

    public ResponseEntity<Void>deleteCar(Long id){
        ResponseEntity<Void> block = webClient.delete()
                .uri("/delete-{id}", id)
                .retrieve()
                .toBodilessEntity()
                .block();
        return block;
    }
}
