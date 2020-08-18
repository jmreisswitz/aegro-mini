package com.jmreisswitz.aegromini.adapters.delivery.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SharedControllerException {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason = "Request with invalid arguments")
    public void illegalArgument(IllegalArgumentException ex){}
}
