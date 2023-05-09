package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CreditCardValidationService {
    public ResponseEntity<CreditCardResponseData> saveCreditCard(CreditCard creditCard);

    public ResponseEntity<CreditCard> getCreditCard(int id);

    public ResponseEntity<List<CreditCard>> getAllCreditCards();

    public ResponseEntity<List<CreditCard>> getAllCreditCardsByCardType(String cardType);

    public ResponseEntity<HttpStatus> deleteCreditCard(int id);

    public ResponseEntity<HttpStatus> deleteAllCreditCards();

    public ResponseEntity<HttpStatus> deleteCreditCardByCardType(String cardType);

}
