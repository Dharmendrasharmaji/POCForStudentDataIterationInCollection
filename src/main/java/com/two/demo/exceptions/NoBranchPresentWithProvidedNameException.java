package com.two.demo.exceptions;

public class NoBranchPresentWithProvidedNameException extends RuntimeException{
    public NoBranchPresentWithProvidedNameException(String message) {
        super(message);
    }
}
