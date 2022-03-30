package fr.jgrand.springpotagercompatibleapi.controller;

import fr.jgrand.springpotagercompatibleapi.exception.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity handleParcelNotFoundException(ApiException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
