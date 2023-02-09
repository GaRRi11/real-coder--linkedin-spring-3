package com.realcoders.realcoderlinkedinspring3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionsHandler {
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException (NullPointerException e){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,badRequest);
    }
    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    public ResponseEntity<Object> hanleEmailAlreadyExistsException (EmailAlreadyExistsException e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(
                e.getMessage(),
                conflict,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,conflict);
    }
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleNullPointerException (UserNotFoundException e){
        HttpStatus badRequest = HttpStatus.UNAUTHORIZED;
        ApiException apiException = new ApiException(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException,badRequest);
    }

}
