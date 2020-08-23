package com.jmreisswitz.aegromini.adapters.delivery.handlers;

import com.jmreisswitz.aegromini.usecases.exceptions.ProductionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductionControllerExceptionHandlers {
    @ExceptionHandler(ProductionNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cant find production registry")
    public void productionNotFound(ProductionNotFoundException ex ) {}
}
