package com.realcoders.realcoderlinkedinspring3.exceptions;

public class InvalidJwtTokenException extends RuntimeException{
    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
