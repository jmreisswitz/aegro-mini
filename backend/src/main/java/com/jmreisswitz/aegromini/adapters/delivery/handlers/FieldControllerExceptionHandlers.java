package com.jmreisswitz.aegromini.adapters.delivery.handlers;

import com.jmreisswitz.aegromini.usecases.exceptions.FieldNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FieldControllerExceptionHandlers {
    @ExceptionHandler(FieldNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cant find field")
    public void fieldNotFound(FieldNotFoundException ex ) {}
}
