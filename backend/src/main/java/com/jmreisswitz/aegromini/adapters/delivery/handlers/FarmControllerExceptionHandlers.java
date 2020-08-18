package com.jmreisswitz.aegromini.adapters.delivery.handlers;

import com.jmreisswitz.aegromini.usecases.exceptions.FarmNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class FarmControllerExceptionHandlers {
    @ExceptionHandler(FarmNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot find farm")
    public void farmNotfound(FarmNotFoundException ex){}
}
