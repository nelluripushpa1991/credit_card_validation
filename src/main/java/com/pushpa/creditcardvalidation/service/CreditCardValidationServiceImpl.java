package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.Constants;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import com.pushpa.creditcardvalidation.repository.CreditCardValidationRepository;
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
        creditCardResponseData.setCardType(Constants.INVALID_CARD_TYPE);
        creditCardResponseData.setMessage(Constants.INVALID);
        if (creditCard != null) {
            int cardNumberLength = creditCard.getCreditCardNumber().length();
            if (cardNumberLength >= Constants.CREDIT_CARD_MIN_LENGTH && cardNumberLength <= Constants.CREDIT_CARD_MAX_LENGTH) {
                creditCardResponseData.setMessage(Constants.VALID);
                if (creditCard.getCreditCardNumber().trim().startsWith(Constants.VISA_SERIES)) {
                    creditCardResponseData.setCardType(Constants.VISA);
                } else if (creditCard.getCreditCardNumber().trim().startsWith(Constants.MASTER_CARD_SERIES)) {
                   creditCardResponseData.setCardType(Constants.MASTER_CARD);
                } else if (creditCard.getCreditCardNumber().trim().startsWith(Constants.AMERICAN_EXPRESS_SERIES)) {
                    creditCardResponseData.setCardType(Constants.AMERICAN_EXPRESS);
                } else {
                    creditCardResponseData.setMessage(Constants.INVALID);
                    creditCardResponseData.setCardType(creditCard.getCardType());
                }
            }
        }
        creditCardValidationRepository.save(creditCard);
        return creditCardResponseData;
    }

    @Override
    public CreditCard getCreditCard(int id) {
        Optional<CreditCard> creditCard = creditCardValidationRepository.findById(id);
        return creditCard.orElse(null);
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
