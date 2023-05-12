package com.pushpa.creditcardvalidation.exception;

public class NoSuchCreditCardExistsException extends RuntimeException{
    private String exceptionMessage;

    public NoSuchCreditCardExistsException() {
    }
    public NoSuchCreditCardExistsException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
}
