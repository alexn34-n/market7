package com.example.exception;

import com.example.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MarketExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorDto> handleResourceNotFound(ResourceNotFoundException exception){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorDto(exception.getMessage()));
    }
}
