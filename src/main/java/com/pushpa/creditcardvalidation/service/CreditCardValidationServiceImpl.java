package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.exception.CreditCardAlreadyExistsException;
import com.pushpa.creditcardvalidation.exception.NoSuchCreditCardExistsException;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import com.pushpa.creditcardvalidation.repository.CreditCardValidationRepository;
import com.pushpa.creditcardvalidation.util.CommonLogics;
import com.pushpa.creditcardvalidation.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService{

    @Autowired
    private CreditCardValidationRepository creditCardValidationRepository;
    @Override
    public CreditCardResponseData saveCreditCard(CreditCard creditCard) {
        CreditCardResponseData creditCardResponseData = new CreditCardResponseData();
        if (creditCard != null) {
            CreditCard saveCreditCardResponse = creditCardValidationRepository.findByCreditCardNumber(creditCard.getCreditCardNumber());
            if (saveCreditCardResponse == null) {
                creditCardResponseData = CommonLogics.getResponseData(creditCard);
                creditCard.setCardType(creditCardResponseData.getCardType());
                creditCardValidationRepository.save(creditCard);
            } else {
                creditCardResponseData.setCardType(creditCard.getCardType());
                creditCardResponseData.setMessage("Invalid Input");
                throw new CreditCardAlreadyExistsException("Credit Card Already Exists");
            }
        }
        return creditCardResponseData;
    }

    @Override
    public CreditCard getCreditCard(int id) {
        Optional<CreditCard> creditCard = creditCardValidationRepository.findById(id);
        return creditCard.orElseThrow(() -> new NoSuchCreditCardExistsException("NO CREDIT CARD PRESENT WITH ID = " + id));
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        List<CreditCard> creditCardList = creditCardValidationRepository.findAll();
        return creditCardList;
    }

    @Override
    public List<CreditCard> getAllCreditCardsByCardType(String cardType) {
        List<CreditCard> creditCardList = creditCardValidationRepository.findByCardType(cardType);
        return creditCardList;
    }
}
