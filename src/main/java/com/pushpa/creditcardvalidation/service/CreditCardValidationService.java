package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;

import java.util.List;

public interface CreditCardValidationService {
    public CreditCard saveCreditCard(CreditCard creditCard);

    public CreditCard getCreditCard(int id);

    public List<CreditCard> getAllCreditCards();

    public List<CreditCard> getAllCreditCardsByCardType(String cardType);

    public void deleteCreditCard(int id);

    public void deleteAllCreditCards();

    public void deleteCreditCardByCardType(String cardType);

}
