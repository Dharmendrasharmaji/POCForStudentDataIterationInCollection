package com.two.demo.exceptions;

public class IdAlreadyExistsException extends RuntimeException{
    public IdAlreadyExistsException(String message) {
        super(message);
    }
}
