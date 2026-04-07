package org.example.resttemplate.exception;

import org.example.resttemplate.ErrorResponse;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String,Object>>>handle(MethodArgumentNotValidException e,
                                                          ServletWebRequest request){
        List<Map<String,Object>>list=new ArrayList<>();

        List<ObjectError> allErrors = e.getAllErrors();
        allErrors.forEach(er->{
            Map<String,Object>map=new HashMap<>();
            map.put("message",er.getDefaultMessage());
            map.put("path",request.getRequest().getRequestURI());
            map.put("timestamp", LocalDateTime.now());
            map.put("code",er.getCode());
            if(er instanceof FieldError fieldError){
                map.put("field",fieldError.getField());
                map.put("rejectedValue",((FieldError) er).getRejectedValue());
            }
            else
                map.put("object",er.getObjectName());
            list.add(map);
        });
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

@ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle1(CarNotFoundException e, ServletWebRequest request){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
    errorResponse.setPath(request.getRequest().getRequestURI());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
}

@ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse>handle2(RuntimeException e,ServletWebRequest request){
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(e.getMessage());
    errorResponse.setPath(request.getRequest().getRequestURI());
    errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
    errorResponse.setTimestamp(LocalDateTime.now());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
}
}
