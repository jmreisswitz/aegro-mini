package com.jmreisswitz.aegromini.adapters.persistence.handler;

import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FieldControllerExceptionHandler {
    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cant find field")
    public void fieldNotFound(FieldNotFoundException ex ) {}
}
