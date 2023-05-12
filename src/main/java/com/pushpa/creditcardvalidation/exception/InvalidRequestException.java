package com.pushpa.creditcardvalidation.exception;

public class InvalidRequestException extends RuntimeException{
    private String exceptionMessage;

    public InvalidRequestException() {
    }
    public InvalidRequestException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
}
