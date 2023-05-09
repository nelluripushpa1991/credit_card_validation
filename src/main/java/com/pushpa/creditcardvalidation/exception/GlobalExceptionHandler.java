package com.pushpa.creditcardvalidation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CreditCardAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCreditCardAlreadyExistsException(CreditCardAlreadyExistsException exception) {
        HttpStatus status = HttpStatus.CONFLICT; // 404
        return new ResponseEntity<>( new ErrorResponse(status.value(), exception.getMessage() ),status);
    }
    @ExceptionHandler(NoSuchCreditCardExistsException.class)
    public ResponseEntity<ErrorResponse> handleCreditCardAlreadyExistsException(NoSuchCreditCardExistsException exception) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        return new ResponseEntity<>( new ErrorResponse(status.value(), exception.getMessage() ),status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleCreditCardAlreadyExistsException(Exception exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        return new ResponseEntity<>( new ErrorResponse(status.value(), exception.getMessage() ),status);
    }

}

