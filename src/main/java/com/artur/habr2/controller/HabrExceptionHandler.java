package com.artur.habr2.controller;

import com.artur.habr2.dto.response.ErrorResponse;
import com.artur.habr2.exception.HabrServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HabrExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(HabrServiceException e, HttpServletRequest r) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(e.getErrorCode())
                .errorDescription(e.getErrorDescription())
                .build(), e.getStatusCode());
    }
}
