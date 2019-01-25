package com.infobit.testtask.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchDataException.class)
    protected ResponseEntity<RestApiException> handleThereIsNoSuchDataException() {
        return new ResponseEntity<>(new RestApiException("There is no such data"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationFailedException.class)
    protected ResponseEntity<RestApiException> handleValidationFailedException() {
        return new ResponseEntity<>(new RestApiException("Validation failed. key: accepts letters, digits, " +
                "underscore, must only start with letter; generation: range: 1..5"), HttpStatus.NOT_FOUND);
    }

    @Data
    @AllArgsConstructor
    private static class RestApiException {
        private String message;
    }
}