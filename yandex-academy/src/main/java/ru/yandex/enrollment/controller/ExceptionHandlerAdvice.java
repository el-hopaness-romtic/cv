package ru.yandex.enrollment.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler
    public ResponseEntity<Error> handleException(IllegalArgumentException e) {
        return new ResponseEntity<>(new Error(BAD_REQUEST.value(), e.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> handleException(NoSuchElementException e) {
        return new ResponseEntity<>(new Error(NOT_FOUND.value(), e.getMessage()), NOT_FOUND);
    }

    record Error(int code, String message) {
    }
}
