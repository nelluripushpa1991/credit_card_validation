package com.pushpa.creditcardvalidation.controller;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import com.pushpa.creditcardvalidation.service.CreditCardValidationService;
import com.pushpa.creditcardvalidation.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card/credit")
@CrossOrigin(origins = "http://localhost:4200")
public class CreditCardValidationController {
    Logger logger = LoggerFactory.getLogger(CreditCardValidationController.class);


    @Autowired
    private CreditCardValidationService creditCardValidationService;

    @PostMapping
    public ResponseEntity<CreditCardResponseData> saveCreditCard(@RequestBody CreditCard cardData) {
        logger.info("Request Date : "+cardData);
        CreditCardResponseData creditCardData = creditCardValidationService.saveCreditCard(cardData);
        logger.info("Response Data from DB : "+creditCardData);
        if (creditCardData != null) {
            if (!Constants.INVALID.equals(creditCardData.getMessage()) && !Constants.INVALID_CARD_TYPE.equals(creditCardData.getCardType())) {
                return ResponseEntity.ok(creditCardData);
            } else {
                return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(creditCardData);
            }
        } else {
            creditCardData = new CreditCardResponseData();
            creditCardData.setCardType(cardData.getCardType());
            creditCardData.setMessage("Internal Server Error");
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(creditCardData);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable int id) {
        CreditCard creditCard = creditCardValidationService.getCreditCard(id);
        System.out.println("response : "+creditCard);
        if (creditCard != null) {
            return ResponseEntity.ok().body(creditCard);
        } else {
            creditCard = new CreditCard();
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(creditCard);
        }


    }

    @GetMapping
    public List<CreditCard> getAllCreditCards() {
        List<CreditCard> creditCardList = creditCardValidationService.getAllCreditCards();
        return creditCardList;
    }

    @GetMapping(path = "/type/{cardType}")
    public List<CreditCard> getAllCreditCardsByCardType(@PathVariable String cardType) {
        List<CreditCard> creditCardList = creditCardValidationService.getAllCreditCardsByCardType(cardType);
        return creditCardList;
    }
}
