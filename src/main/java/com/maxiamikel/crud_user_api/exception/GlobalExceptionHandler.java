package com.maxiamikel.crud_user_api.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Validation error");
        response.put("errors", ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList()));

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ObjectIdNotFoundException.class)
    public ResponseEntity<StandardError> objectIdNotFoundException(ObjectIdNotFoundException ex) {
        var error = StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<StandardError>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicationCreateUserEmailException.class)
    public ResponseEntity<StandardError> duplicationCreateUserEmailException(DuplicationCreateUserEmailException ex) {
        var error = StandardError.builder()
                .status(HttpStatus.CONFLICT.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<StandardError>(error, HttpStatus.CONFLICT);
    }
}
