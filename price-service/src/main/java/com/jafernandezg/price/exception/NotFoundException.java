package com.jafernandezg.price.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private int httpCode;
    private String message;

    public NotFoundException(String message) {
        super(message);
        this.httpCode = 404;
        this.message = message;
    }

}
