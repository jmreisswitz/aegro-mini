package com.jmreisswitz.aegromini.usecases.exceptions;

public class FarmNotFoundException extends RuntimeException {
    public FarmNotFoundException(String message){
        super(message);
    }
}
