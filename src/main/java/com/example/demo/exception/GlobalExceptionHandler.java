package com.example.demo.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*  @ExceptionHandler(MethodArgumentNotValidException.class)
      public ResponseEntity<Map<String,Object>> handle(MethodArgumentNotValidException ex){
          Map<String, Object> map = new HashMap<>();
          ex.getBindingResult().getFieldErrors().forEach(error->
                  map.put(error.getField(),error.getDefaultMessage()));
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
      }*/
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle1(EntityNotFoundException ex, ServletWebRequest request) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", ex.getMessage());
        map.put("status", HttpStatus.NOT_FOUND.value());
        map.put("path", request.getRequest().getRequestURI());
        map.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handle2(UserNotFoundException ex, ServletWebRequest request) {
        Map<Object, Object> map = new HashMap<>();
        return new ResponseEntity<>(Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.NOT_FOUND.value(),
                "path", request.getRequest().getRequestURI(),
                "timestamp", LocalDateTime.now()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Map<String, Object>> handle3(UserAlreadyExistException ex, ServletWebRequest request) {
        return new ResponseEntity<>(Map.of(
                "message", ex.getMessage(),
                "status", HttpStatus.BAD_REQUEST,
                "path", request.getRequest().getRequestURI(),
                "timestamp", LocalDateTime.now()), HttpStatus.BAD_REQUEST
        );
    }

/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, Object>>> handle4(MethodArgumentNotValidException ex,
                                                             ServletWebRequest request) {
        List<FieldError> fieldErrors = ex.getFieldErrors();

        List<Map<String, Object>> list = new ArrayList<>();
        fieldErrors.forEach(e -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Message", e.getDefaultMessage());
            map.put("Path", request.getRequest().getRequestURI());
            map.put("Timestamp", LocalDateTime.now());
            map.put("Field", e.getField());
            map.put("RejectedValue", e.getRejectedValue());
            map.put("Code", e.getCode());
            list.add(map);
        });
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle5(CarNotFoundException e, ServletWebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setPath(request.getRequest().getRequestURI());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CarAlreadyExistException.class)
    public ResponseEntity<ErrorResponse> handle6(CarAlreadyExistException e, ServletWebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(HttpStatus.CONFLICT.value());
        errorResponse.setPath(request.getRequest().getRequestURI());
        errorResponse.setTimestamp(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<Map<String, Object>>> handle7(ConstraintViolationException e, ServletWebRequest request) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<Map<String, Object>> list = new ArrayList<>();
        constraintViolations.forEach(cv -> {
            Map<String, Object> map = new HashMap<>();
            map.put("message ", cv.getMessage());
            // map.put("path ", cv.getPropertyPath().toString());
            map.put("path", request.getRequest().getRequestURI());
            map.put("invalid value ", cv.getInvalidValue());
            map.put("timestamp ", LocalDateTime.now());
            list.add(map);
        });
        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, Object>>> handle8
            (MethodArgumentNotValidException e, ServletWebRequest request) {
        List<ObjectError> allErrors = e.getAllErrors();
        List<Map<String, Object>> list = new ArrayList<>();
        allErrors.forEach(er -> {
            Map<String, Object> map = new HashMap<>();
            map.put("message", er.getDefaultMessage());
            map.put("path", request.getRequest().getRequestURI());
            map.put("timestamp", LocalDateTime.now());
            map.put("code", er.getCode());
            if (er instanceof FieldError fieldError) {
                map.put("field", fieldError.getField());
                map.put("rejectedValue", fieldError.getRejectedValue());
            } else
                map.put("object", er.getObjectName());
            list.add(map);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<Map<String, Object>>> handle9(Exception e, ServletWebRequest request) {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = Map.of("message", e.getMessage()
                , "path", request.getRequest().getRequestURI()
                , "status", HttpStatus.BAD_REQUEST
                , "timestamp", LocalDateTime.now()
                , "exceptionType", e.getClass().getName());
        list.add(map);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(list);


    }
}
