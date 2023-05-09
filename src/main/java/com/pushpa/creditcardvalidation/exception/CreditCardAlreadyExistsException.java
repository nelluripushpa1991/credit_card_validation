package com.pushpa.creditcardvalidation.exception;

public class CreditCardAlreadyExistsException extends RuntimeException {
    private String exceptionMessage;

    public CreditCardAlreadyExistsException() {
    }
    public CreditCardAlreadyExistsException(String exceptionMessage) {
        super(exceptionMessage);
        this.exceptionMessage = exceptionMessage;
    }
}
