package org.example.resttemplate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;

import org.example.resttemplate.ErrorResponse;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.dto.CarResponseDto;
import org.example.resttemplate.exception.CarNotFoundException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestClientService {


    private final RestClient restClient;

    public ResponseEntity<CarResponseDto>getCarById(Long id){
        ResponseEntity<CarResponseDto> entity = restClient
                .get()
                .uri("/get-{id}",id)
                .retrieve()
                .toEntity(CarResponseDto.class);
        return entity;
    }

    public ResponseEntity<?> getCarById1(Long id) throws JsonProcessingException {
        try{
        CarResponseDto body = restClient
                .get()
                .uri("/get-{id}",id)
                .retrieve()
                .body(CarResponseDto.class);
        return ResponseEntity.ok(body);
    }
        catch (HttpClientErrorException e){
            HttpStatusCode statusCode = e.getStatusCode();
            String responseBodyAsString = e.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ErrorResponse errorResponse = objectMapper.readValue(responseBodyAsString, ErrorResponse.class);
            return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        }
        catch (HttpServerErrorException ex){
            HttpStatusCode statusCode = ex.getStatusCode();
            String responseBodyAsString = ex.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ErrorResponse errorResponse = objectMapper.readValue(responseBodyAsString, ErrorResponse.class);
            return ResponseEntity.status(statusCode).body(errorResponse);
        }
        catch (Exception exx){
            throw new RuntimeException("Server Exception");
        }}

    public void getCarById2(Long id){
        restClient
                .get()
                .uri("/get-{id}",id)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<List<CarResponseDto>>getCars(){
        ResponseEntity<List<CarResponseDto>> entity = restClient
                .get()
                .uri("/get")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<List<CarResponseDto>>() {
                });
        return entity;
    }

    public ResponseEntity<CarResponseDto>addCar(CarDto1 dto){
        ResponseEntity<CarResponseDto> entity = restClient
                .post()
                .uri("/post")
                .header("Content-Type", "application/json")
                .body(dto)
                .retrieve()
                .toEntity(CarResponseDto.class);
        return entity;
    }

    public CarResponseDto addCar1(CarDto1 dto){
        CarResponseDto body = restClient
                .post()
                .uri("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .body(dto)
                .retrieve()
                .body(CarResponseDto.class);
        return body;
    }

    public void addCar2(CarDto1 dto){
        restClient
                .post()
                .uri("/post")
                .header("Content-Type","application/json")
                .body(dto)
                .retrieve()
                .toBodilessEntity();
    }

    public ResponseEntity<CarResponseDto>updateCar(Long id,CarDto1 dto){
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/api/v1/car/put-{id}")
                .buildAndExpand(id)
                .toUri();
        ResponseEntity<CarResponseDto> entity = restClient
                .put()
                .uri(uri)
                .body(dto)
                .retrieve()
                .toEntity(CarResponseDto.class);
        return entity;
    }

    public ResponseEntity<Void> deleteCar(Long id){
        ResponseEntity<Void> bodilessEntity = restClient
                .delete()
                .uri("/delete-{id}",id)
                .retrieve()
                .toBodilessEntity();
        return bodilessEntity;
    }

    public ResponseEntity<?>getCarByModel(String model){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/by-model")
                .queryParam("model", model)
                .build()
                .toUri();
        ResponseEntity<CarResponseDto> entity = restClient
                .get()
               // .uri("/by-model?model={model}", model)
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,((request,response)->{
                    if(response.getStatusCode()== HttpStatus.NOT_FOUND)
                        throw new CarNotFoundException("Car not found");
                 /*   else if(response.getStatusCode()==HttpStatus.BAD_REQUEST)
                        throw new BadRequestException("Bad request");*/
                }))
               /* .onStatus(HttpStatusCode::is5xxServerError,((request,response)-> {
                    throw new RuntimeException("Server exception");
                }))*/
                .toEntity(CarResponseDto.class);
        return entity;
    }
}
