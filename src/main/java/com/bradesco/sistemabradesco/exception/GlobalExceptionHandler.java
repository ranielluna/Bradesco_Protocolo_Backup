package com.bradesco.sistemabradesco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Trata o erro de funcionário não encontrado
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        return "Funcionário não encontrado: " + ex.getMessage();
    }

    // Trata o erro de funcionário não autorizado
    @ExceptionHandler(NotAuthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleNotAuthorizedException(NotAuthorizedException ex) {
        return "Acesso não autorizado: " + ex.getMessage();
    }

}