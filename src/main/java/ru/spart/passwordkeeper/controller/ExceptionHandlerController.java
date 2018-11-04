package ru.spart.passwordkeeper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    protected ResponseEntity<Void> authentificationNotFound() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
