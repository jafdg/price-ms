package com.jafernandezg.price.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends RuntimeException {

    private int httpCode;
    private String message;

    public BadRequestException(String message) {
        super(message);
        this.httpCode = 400;
        this.message = message;
    }

}
