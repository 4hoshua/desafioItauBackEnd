package com.desafio.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<ExceptionDetails> handleUnprocessableEntityException(UnprocessableEntityException UnprocessableEntityException) {
        ExceptionDetails details = new ExceptionDetails(HttpStatus.UNPROCESSABLE_ENTITY.value(), UnprocessableEntityException.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
