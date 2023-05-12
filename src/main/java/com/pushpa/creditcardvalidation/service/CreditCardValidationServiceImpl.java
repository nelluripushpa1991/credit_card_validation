package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.exception.CreditCardAlreadyExistsException;
import com.pushpa.creditcardvalidation.exception.InvalidRequestException;
import com.pushpa.creditcardvalidation.exception.NoSuchCreditCardExistsException;
import com.pushpa.creditcardvalidation.repository.CreditCardValidationRepository;
import com.pushpa.creditcardvalidation.util.CommonLogics;
import com.pushpa.creditcardvalidation.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService{

    @Autowired
    private CreditCardValidationRepository creditCardValidationRepository;
    @Override
    public CreditCard saveCreditCard(CreditCard creditCard ) {
        if (creditCard != null) {
            CreditCard cardResponseFromDB = creditCardValidationRepository.findByCreditCardNumber(creditCard.getCreditCardNumber());
            if (cardResponseFromDB == null) {
                // getting card type based on number series
                creditCard.setCardType(CommonLogics.getCardType(creditCard.getCreditCardNumber()));
                // validate input
                if (CommonLogics.validateCreditCard(creditCard.getCreditCardNumber(), creditCard.getCardType())) {
                    return creditCardValidationRepository.save(creditCard);
                } else {
                     throw new InvalidRequestException(Constants.BAD_REQUEST_MESSAGE);
                }
            } else {
                throw new CreditCardAlreadyExistsException(Constants.CONFLICT_MESSAGE);
            }
        }
        return null;
    }

    @Override
    public CreditCard getCreditCard(int id) {
        CreditCard creditCard;
        if (creditCardValidationRepository.findById(id).isEmpty()) {
            throw new NoSuchCreditCardExistsException(Constants.DATA_NOT_EXIST_MESSAGE);
        } else {
            creditCard = creditCardValidationRepository.findById(id).get();
        }
        return creditCard;
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        List<CreditCard> creditCardList = creditCardValidationRepository.findAll();
        if (creditCardList == null)
            throw new NoSuchCreditCardExistsException(Constants.DATA_NOT_EXIST_MESSAGE);
        else
            return creditCardList;
    }

    @Override
    public List<CreditCard> getAllCreditCardsByCardType(String cardType) {
        List<CreditCard> creditCardList = new ArrayList<>();
        if (cardType != null)
            creditCardList.addAll(creditCardValidationRepository.findByCardType(cardType));
        else
            creditCardList.addAll(creditCardValidationRepository.findAll());
        return creditCardList;
    }

    @Override
    public void deleteCreditCard(int id) {
        creditCardValidationRepository.deleteById(id);
    }

    @Override
    public void deleteAllCreditCards() {
        creditCardValidationRepository.deleteAll();
    }

    @Override
    public void deleteCreditCardByCardType(String cardType) {
        if (cardType != null)
            creditCardValidationRepository.deleteByCardType(cardType);
        else
            throw new NoSuchCreditCardExistsException(Constants.DATA_NOT_EXIST_MESSAGE);
    }
}
