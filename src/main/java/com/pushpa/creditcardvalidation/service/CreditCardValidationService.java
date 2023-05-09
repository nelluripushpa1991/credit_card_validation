package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;

import java.util.List;

public interface CreditCardValidationService {
    public CreditCardResponseData saveCreditCard(CreditCard creditCard);

    public CreditCard getCreditCard(int id);

    public List<CreditCard> getAllCreditCards();

    public List<CreditCard> getAllCreditCardsByCardType(String cardType);

}
