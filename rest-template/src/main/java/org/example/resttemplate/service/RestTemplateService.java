package org.example.resttemplate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.example.resttemplate.dto.CarDto1;
import org.example.resttemplate.ErrorResponse;
import org.example.resttemplate.dto.CarResponseDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public CarResponseDto getCarById(Long id) {
        return restTemplate.getForObject
                ("http://localhost:8080/api/v1/car/get-{id}", CarResponseDto.class, id);
    }

    public ResponseEntity<?> getCarDetail(Long id) {
        try {
            ResponseEntity<CarResponseDto> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/car/get-{id}",
                    CarResponseDto.class, id);
            return forEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            return new ResponseEntity<>(responseBodyAsString, statusCode);
        } catch (Exception e) {
            String message = e.getMessage();
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(500);
            return new ResponseEntity<>(message, httpStatusCode);
        }

    }


    public ResponseEntity<?> getCarByModel(String model) {
        try {
            ResponseEntity<CarResponseDto> forEntity = restTemplate.getForEntity(
                    "http://localhost:8080/api/v1/car/by-model?model={model}",
                    CarResponseDto.class, model);
            return forEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            return new ResponseEntity<>(responseBodyAsString, statusCode);
        } catch (Exception e) {
            String message = e.getMessage();
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(500);
            return new ResponseEntity<>(message, httpStatusCode);
        }
    }

    public ResponseEntity<?> getCarById1(Long id) {
        try {
            URI uri = UriComponentsBuilder.
                    fromUriString("http://localhost:8080/api/v1/car/get-{id}")
                    .buildAndExpand(id)
                    .toUri();
            ResponseEntity<CarResponseDto> forEntity = restTemplate.getForEntity(uri, CarResponseDto.class);
            return forEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            return ResponseEntity.status(statusCode).body(responseBodyAsString);
        } catch (Exception e) {
            String message = e.getMessage();
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(500);
            return ResponseEntity.status(httpStatusCode).body(message);
        }
    }

    public ResponseEntity<?> getCarByModel1(String model) {
        try {
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/by-model")
                    .queryParam("model", model)
                    .build()
                    .toUri();
            ResponseEntity<CarResponseDto> forEntity = restTemplate.getForEntity(uri, CarResponseDto.class);
            return forEntity;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            return ResponseEntity.status(statusCode).body(responseBodyAsString);
        } catch (Exception e) {
            String message = e.getMessage();
            HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(500);
            return ResponseEntity.status(httpStatusCode).body(message);
        }
    }

    public int sum(int value1, int value2) {
        Map<String, Object> map = new HashMap<>();
        map.put("value1", value1);
        map.put("value2", value2);
        Integer forObject = restTemplate.getForObject("http://localhost:8080/demo/api/v1/sum/{value1}/{value2}"
                , Integer.class, map);
        return forObject;
    }

    public int sum1(int value1, int value2, int value3) {
        Map<String, Object> map = new HashMap<>();
        map.put("value1", value1);
        map.put("value2", value2);
        map.put("value3", value3);
        Integer forObject = restTemplate.
                getForObject("http://localhost:8080/demo/api/v1/sum1?value1={1}&value2={2}&value3={3}"
                        , Integer.class, map);
        return forObject;
    }

    public int sum2(int value1, int value2) {
        String uriString = UriComponentsBuilder.fromUriString("http://localhost:8080/demo/api/v1/sum/{value1}/{value2}")
                .buildAndExpand(value1, value2).toUriString();
        Integer forObject = restTemplate.getForObject(uriString, Integer.class);
        return forObject;
    }

    public int sum3(int value1, int value2, int value3) {
        String uriString = UriComponentsBuilder.fromUriString("http://localhost:8080/demo/api/v1/sum1")
                .queryParam("value1", value1)
                .queryParam("value2", value2)
                .queryParam("value3", value3)
                .build()
                .toUriString();
        return restTemplate.getForObject(uriString, Integer.class);
    }

    public CarResponseDto getCarById2(Long id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Content-Type", List.of(MediaType.APPLICATION_JSON.toString()));
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        String path = "http://localhost:8080/api/v1/car/get-{id}";
        ResponseEntity<CarResponseDto> exchange = restTemplate.
                exchange(path, HttpMethod.GET, httpEntity, CarResponseDto.class, id);
        CarResponseDto body = exchange.getBody();
        return body;
    }

    public CarResponseDto getCarById3(Long id) {
        String path = "http://localhost:8080/api/v1/car/get-{id}";
        URI uri = UriComponentsBuilder.fromUriString(path)
                .buildAndExpand(id)
                .toUri();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put("Content-Type", List.of(MediaType.APPLICATION_JSON.toString()));
        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CarResponseDto> exchange = restTemplate.exchange(uri, HttpMethod.GET, httpEntity,
                CarResponseDto.class);
        CarResponseDto body = exchange.getBody();
        return body;
    }

    public CarResponseDto getCarByModel2(String model) {
        String path = "http://localhost:8080/api/v1/car/by-model?model={model}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<CarResponseDto> exchange = restTemplate.
                exchange(path, HttpMethod.GET, httpEntity, CarResponseDto.class, model);
        CarResponseDto body = exchange.getBody();
        return body;
    }

    public CarResponseDto getCarByModel3(String model) {
        String path = "http://localhost:8080/api/v1/car/by-model";
        URI uri = UriComponentsBuilder.fromUriString(path)
                .queryParam("model", model)
                .build()
                .toUri();

        ResponseEntity<CarResponseDto> exchange = restTemplate
                .exchange(uri, HttpMethod.GET, null, CarResponseDto.class);
        CarResponseDto body = exchange.getBody();
        return body;
    }

    public ResponseEntity<?> repeat(int count, String word) {
        try {
            String path = "http://localhost:8080/demo/api/v1/repeat/{count}";
            URI uri = UriComponentsBuilder.fromUriString(path)
                    .queryParam("word", word)
                    .buildAndExpand(count)
                    .toUri();
            ResponseEntity<String> exchange = restTemplate
                    .exchange(uri, HttpMethod.GET, null, String.class);

            return exchange;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            return ResponseEntity.status(statusCode).body(responseBodyAsString);
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("Service unavaliable: " + e.getMessage());
        }
    }

    public ResponseEntity<?> getCarById4(Long id) throws JsonProcessingException {
        try {
            URI uri = UriComponentsBuilder
                    .fromUriString("http://localhost:8080/api/v1/car/get-by-id/{id}")
                    .buildAndExpand(id)
                    .toUri();
            ResponseEntity<CarResponseDto> exchange = restTemplate
                    .exchange(uri, HttpMethod.GET, null, CarResponseDto.class);
            return exchange;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            ErrorResponse errorResponse = objectMapper
                    .readValue(responseBodyAsString, ErrorResponse.class);
            log.error("An error occurred: {}", errorResponse.getMessage());
            HttpStatusCode statusCode = e.getStatusCode();
            return ResponseEntity.status(statusCode).body(errorResponse);
        } catch (ResourceAccessException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(e.getMessage());
        }
    }

    public ResponseEntity<List<CarResponseDto>> getCars() {
        ResponseEntity<List<CarResponseDto>> exchange =
                 restTemplate.exchange("http://localhost:8080/api/v1/car/get"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<List<CarResponseDto>>() {
                });
        return exchange;
    }

    public ResponseEntity<?> addCar(CarDto1 dto) throws JsonProcessingException {
        try {
            CarResponseDto carResponseDto = restTemplate
                    .postForObject("http://localhost:8080/api/v1/car/post1"
                            , dto
                            , CarResponseDto.class);
            return ResponseEntity.ok(carResponseDto);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<Map<String, Object>> list = objectMapper
                    .readValue(responseBodyAsString,
                            new TypeReference<List<Map<String, Object>>>() {
                            });
            return ResponseEntity.status(statusCode).body(list);
        }
    }

    public ResponseEntity<?> addCar1(CarDto1 dto) throws JsonProcessingException {
        try {
            ResponseEntity<CarResponseDto> carResponseDtoResponseEntity = restTemplate
                    .postForEntity("http://localhost:8080/api/v1/car/post1"
                            , dto
                            , CarResponseDto.class);
            return carResponseDtoResponseEntity;
        } catch (HttpServerErrorException | HttpClientErrorException e) {
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            List<Map<String, Object>> list = objectMapper.readValue(responseBodyAsString,
                    new TypeReference<List<Map<String, Object>>>() {
                    });
            return ResponseEntity.status(statusCode).body(list);
        }
    }

    public ResponseEntity<?> addCar2(CarDto1 dto) throws JsonProcessingException {

        try {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CarDto1> carDto1HttpEntity = new HttpEntity<>(dto, httpHeaders);
            ResponseEntity<CarResponseDto> exchange = restTemplate
                    .exchange("http://localhost:8080/api/v1/car/post1"
                            , HttpMethod.POST
                            , carDto1HttpEntity
                            , CarResponseDto.class);
            return exchange;
        } catch (HttpClientErrorException | HttpServerErrorException e) {


            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
            if (jsonNode.isArray()) {
                return ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode, new TypeReference<List<Map<String, Object>>>() {
                        }));
            }
            try {
                return ResponseEntity.status(statusCode).
                        body(objectMapper.treeToValue(jsonNode, ErrorResponse.class));
            }
            catch (Exception ex) {
                return ResponseEntity.status(statusCode).body(objectMapper.convertValue(jsonNode,
                        new TypeReference<Map<String, Object>>() {
                }));
            }

        }
    }

 /*   RestTemplate:
            2xx → normal return
            4xx → HttpClientErrorException
    5xx → HttpServerErrorException*/

    public ResponseEntity<?>addCar3(String model, BigDecimal price,CarDto1 dto) throws JsonProcessingException {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<CarDto1> carDto1HttpEntity = new HttpEntity<>(dto, httpHeaders);
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/post/{model}")
                    .queryParam("price", price)
                    .buildAndExpand(model)
                    .toUri();
            ResponseEntity<CarResponseDto> exchange = restTemplate
                    .exchange(uri, HttpMethod.POST, carDto1HttpEntity, CarResponseDto.class);
return exchange;
        }
        catch (HttpClientErrorException |HttpServerErrorException e){
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
            if(jsonNode.isArray()){
                return ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode,new TypeReference<List<Map<String,Object>>>(){}));
            }
            try {
                return ResponseEntity.status(statusCode).body(new TypeReference<Map<String, Object>>() {
                });
            }
            catch (Exception ex){
                return ResponseEntity.status(statusCode).body(objectMapper.treeToValue(jsonNode, ErrorResponse.class));
            }
        }
    }

    public ResponseEntity<?>deleteCar(Long id) throws JsonProcessingException {
        try{
        String path="http://localhost:8080/api/v1/car/delete-{id}";
        restTemplate.delete(path,id);
        return ResponseEntity.noContent().build();}
        catch (HttpClientErrorException|HttpServerErrorException e){
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
            if(jsonNode.isArray())
            return ResponseEntity.status(statusCode).body(objectMapper
                    .convertValue(jsonNode, new TypeReference<List<Map<String,Object>>>() {}));

            try{
                return ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode,new TypeReference<Map<String,Object>>() {}));
            }
            catch (Exception ex){
               return ResponseEntity.status(statusCode).body(objectMapper.treeToValue(jsonNode,ErrorResponse.class));
            }

        }


}


public ResponseEntity<?>deleteCar1(Long id) throws JsonProcessingException{
        try{
            URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/delete-{id}")
                    .buildAndExpand(id)
                    .toUri();
            ResponseEntity<?> exchange =
                    restTemplate.exchange(uri, HttpMethod.DELETE,null, Void.class);
          //  return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            return exchange;
        }
        catch (HttpClientErrorException|HttpServerErrorException e){
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
            if(jsonNode.isArray())
                return ResponseEntity.status(statusCode)
                        .body(objectMapper.convertValue(jsonNode,
                                new TypeReference<List<Map<String,Object>>>() {}));
            try{
              return  ResponseEntity.status(statusCode)
                        .body(objectMapper.treeToValue(jsonNode,ErrorResponse.class));
            }
            catch (Exception ex){
              return  ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode, new TypeReference<Map<String,Object>>() {}));
            }
        }

}


public ResponseEntity<?>updateCar(Long id,CarDto1 dto) throws JsonProcessingException {
   try{
    URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/update1/{id}")
            .buildAndExpand(id)
            .toUri();
    restTemplate.put(uri,dto);
    return ResponseEntity.ok().build();
}
   catch (HttpClientErrorException|HttpServerErrorException e){
       String responseBodyAsString = e.getResponseBodyAsString();
       HttpStatusCode statusCode = e.getStatusCode();
       ObjectMapper objectMapper = new ObjectMapper();
       JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
       if(jsonNode.isArray())
           return ResponseEntity.ok(objectMapper.convertValue(jsonNode,
                   new TypeReference<List<Map<String,Object>>>() {}));

      else if(jsonNode.has("message")&&jsonNode.has("path")&&jsonNode.has("status")
               &&jsonNode.has("timestamp"))
          return ResponseEntity.ok(objectMapper.treeToValue(jsonNode,ErrorResponse.class));

      else
          return ResponseEntity.ok(objectMapper
                  .convertValue(jsonNode, new TypeReference<Map<String,Object>>() {
          }));
   }
}


public ResponseEntity<?>updateCar1(Long id,CarDto1 dto) throws JsonProcessingException {
        try{
    URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/api/v1/car/update1/{id}")
            .buildAndExpand(id)
            .toUri();
    HttpEntity<CarDto1> carDto1HttpEntity = new HttpEntity<>(dto);

    return restTemplate.exchange(uri, HttpMethod.PUT, carDto1HttpEntity, CarResponseDto.class);
}
        catch(HttpClientErrorException|HttpServerErrorException e){
            String responseBodyAsString = e.getResponseBodyAsString();
            HttpStatusCode statusCode = e.getStatusCode();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(responseBodyAsString);
            if(jsonNode.isArray())
                return ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode, new TypeReference<List<Map<String,Object>>>() {
                }));
            try{
                return ResponseEntity.status(statusCode).body(objectMapper
                        .convertValue(jsonNode, new TypeReference<Map<String,Object>>() {
                }));
            }
            catch (Exception ex){
                return ResponseEntity.status(statusCode).body(objectMapper
                        .treeToValue(jsonNode, ErrorResponse.class));
            }
        }
}}