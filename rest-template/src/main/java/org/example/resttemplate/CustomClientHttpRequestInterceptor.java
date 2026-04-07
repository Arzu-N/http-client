package org.example.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

@Slf4j
public class CustomClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[]body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("ozel","gizli bilgi");
        log.info("uri {}",request.getURI());
        log.info("method {}",request.getMethod());
        log.info("headers {}",request.getHeaders());
        return execution.execute(request,body);
    }
}
