package com.two.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> invalidConstraintHandler(MethodArgumentNotValidException exception){
        HashMap<Object, Object> mapError = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->mapError.put(error.getField(),error.getDefaultMessage()));
        return new ResponseEntity<>(mapError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<?> noDataFoundHandler(NoDataFoundException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IdAlreadyExistsException.class)
    public ResponseEntity<?> idAlreadyPresentHandler(IdAlreadyExistsException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoBranchPresentWithProvidedNameException.class)
    public ResponseEntity<?> branchNotPresentHandler(NoBranchPresentWithProvidedNameException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

}
