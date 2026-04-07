package org.example.resttemplate.config;

import org.example.resttemplate.CustomClientHttpRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpHeaders;
import java.util.List;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.setInterceptors(List.of(new CustomClientHttpRequestInterceptor()));
        return restTemplate;
    }

    @Bean
    public RestClient restClient(){
       return  RestClient.builder()
                .baseUrl("http://localhost:8080/api/v1/car")
                .defaultHeader("Accept","application/json")
                .build();
    }

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .baseUrl("http://localhost:8080/api/v1/car")
                .defaultHeader("Accept", "application/json")
                .build();
    }
}
