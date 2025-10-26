package com.capymenu.capy_menu_api.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.capymenu.capy_menu_api.models.response.ResponseGeneric;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseGeneric<String>> handleGlobalException(Exception e, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseGeneric.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseGeneric<Map<String, String>>> handleBadRequest(MethodArgumentNotValidException e) {
        Map<String, String> errorByField = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(field -> 
            errorByField.put(field.getField(), field.getDefaultMessage()));
        
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ResponseGeneric.of(HttpStatus.BAD_REQUEST, e.getMessage(), errorByField));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ResponseGeneric<String>> handleUnauthorized(AuthenticationException e, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ResponseGeneric.of(HttpStatus.UNAUTHORIZED, e.getMessage(), request.getDescription(false)));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseGeneric<String>> handleResourceNotFound(ResourceNotFoundException e, WebRequest request) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ResponseGeneric.of(HttpStatus.NOT_FOUND, e.getMessage(), request.getDescription(false)));
    }

}
