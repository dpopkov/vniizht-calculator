package io.dpopkov.zhtcalculator.controllers;

import io.dpopkov.zhtcalculator.calculator.CalculatorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionHandling {
    private static final String INTERNAL_SERVER_ERROR_MSG = "An error occurred while processing the request";

    @ExceptionHandler(CalculatorException.class)
    public ResponseEntity<AppHttpResponse> calculatorException(CalculatorException ex) {
        return createResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppHttpResponse> internalServerErrorException(Exception exception) {
        log.error("Server Exception: ", exception);
        return createResponse(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MSG);
    }

    private ResponseEntity<AppHttpResponse> createResponse(HttpStatus status, String message) {
        log.error("Error status: '{}', message: '{}'", status.value(), message);
        return new ResponseEntity<>(new AppHttpResponse(status, message), status);
    }
}
