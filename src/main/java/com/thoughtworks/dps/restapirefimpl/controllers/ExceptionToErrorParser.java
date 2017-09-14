package com.thoughtworks.dps.restapirefimpl.controllers;

import com.thoughtworks.dps.restapirefimpl.entities.ErrorResponse;
import com.thoughtworks.dps.restapirefimpl.exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class ExceptionToErrorParser {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequest(BadRequestException e) {
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), BAD_REQUEST);
    }
}
