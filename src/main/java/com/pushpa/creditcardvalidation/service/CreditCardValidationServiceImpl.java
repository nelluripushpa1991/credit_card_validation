package com.pushpa.creditcardvalidation.service;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.exception.CreditCardAlreadyExistsException;
import com.pushpa.creditcardvalidation.exception.NoSuchCreditCardExistsException;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import com.pushpa.creditcardvalidation.repository.CreditCardValidationRepository;
import com.pushpa.creditcardvalidation.util.CommonLogics;
import com.pushpa.creditcardvalidation.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CreditCardValidationServiceImpl implements CreditCardValidationService{

    @Autowired
    private CreditCardValidationRepository creditCardValidationRepository;
    @Override
    public ResponseEntity<CreditCardResponseData> saveCreditCard(CreditCard creditCard ){
        CreditCardResponseData creditCardResponseData = new CreditCardResponseData();
        try {
            if (creditCard != null) {
                CreditCard saveCreditCardResponse = creditCardValidationRepository.findByCreditCardNumber(creditCard.getCreditCardNumber());
                if (saveCreditCardResponse == null) {
                    // getting response based on given conditions
                    creditCardResponseData = CommonLogics.getResponseData(creditCard);
                    creditCard.setCardType(creditCardResponseData.getCardType());
                    creditCardValidationRepository.save(creditCard);
                    return new ResponseEntity<>(creditCardResponseData, HttpStatus.CREATED);
                } else {
                    // not allowing to save same card again
                    creditCardResponseData.setCardType(creditCard.getCardType());
                    creditCardResponseData.setMessage("Invalid Input");
                    return new ResponseEntity<>(creditCardResponseData, HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<>(creditCardResponseData, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(creditCardResponseData, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<CreditCard> getCreditCard(int id) {
        Optional<CreditCard> creditCard = creditCardValidationRepository.findById(id);
        return creditCard.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> creditCardList = creditCardValidationRepository.findAll();
        return new ResponseEntity<>(creditCardList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CreditCard>> getAllCreditCardsByCardType(String cardType) {
        try {
            List<CreditCard> creditCardList = new ArrayList<>();
            if (cardType == null)
                creditCardList.addAll(creditCardValidationRepository.findAll());
            else
                creditCardList.addAll(creditCardValidationRepository.findByCardType(cardType));
            if (creditCardList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(creditCardList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCreditCard(int id) {
        try {
            creditCardValidationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteAllCreditCards() {
        try {
            creditCardValidationRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<HttpStatus> deleteCreditCardByCardType(String cardType) {
        try {
            creditCardValidationRepository.deleteByCardType(cardType);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
