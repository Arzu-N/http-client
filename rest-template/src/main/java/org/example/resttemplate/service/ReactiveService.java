package org.example.resttemplate.service;

import jdk.jfr.Frequency;
import lombok.RequiredArgsConstructor;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReactiveService {
private final WebClient webClient;


    public Flux<CarResponseDto>getCars(){
        Flux<CarResponseDto> carResponseDtoFlux = webClient.get()
                .uri("/get")
                .retrieve()
                .bodyToFlux(CarResponseDto.class);
        return carResponseDtoFlux;

    }

    public Mono<CarResponseDto>getCarById(Long id){
        Mono<CarResponseDto> carResponseDtoMono = webClient.get()
                .uri("/get-{id}",id)
                .retrieve()
                .bodyToMono(CarResponseDto.class);
        return carResponseDtoMono;
    }

    public Mono<CarResponseDto>getCarByModel(String model){
        Mono<CarResponseDto> carResponseDtoMono = webClient.get()
                .uri("/by-model?model={model}", model)
                .retrieve()
                .bodyToMono(CarResponseDto.class);
        return carResponseDtoMono;
    }

    public Mono<CarResponseDto>addCar(CarDto1 dto){
        Mono<CarResponseDto> carResponseDtoMono = webClient.post()
                .uri("/post1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(CarResponseDto.class);
        return carResponseDtoMono;
    }

    public Mono<CarResponseDto>updateCar(Long id,CarDto1 dto){
        Mono<CarResponseDto> carResponseDtoMono = webClient.put()
                .uri("/put-{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(dto)
                .retrieve()
                .bodyToMono(CarResponseDto.class);
        return carResponseDtoMono;
    }

    public Mono<ResponseEntity<Void>>deleteCar(Long id){
        Mono<ResponseEntity<Void>> bodilessEntity = webClient.delete()
                .uri("/delete-{id}",id)
                .retrieve()
                .toBodilessEntity();
        return bodilessEntity;
    }
}
