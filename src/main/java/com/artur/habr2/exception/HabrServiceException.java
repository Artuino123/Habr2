package com.artur.habr2.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HabrServiceException extends RuntimeException {
    private final String errorCode;
    private final String errorDescription;
    private final HttpStatus statusCode;

    public HabrServiceException(String errorCode, String errorDescription, HttpStatus statusCode) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
        this.statusCode = statusCode;
    }
}