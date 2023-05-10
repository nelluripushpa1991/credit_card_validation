package com.pushpa.creditcardvalidation.controller;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;
import com.pushpa.creditcardvalidation.service.CreditCardValidationService;
import com.pushpa.creditcardvalidation.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/card/credit")
public class CreditCardValidationController {
    Logger logger = LoggerFactory.getLogger(CreditCardValidationController.class);


    @Autowired
    private CreditCardValidationService creditCardValidationService;

    @PostMapping
    public ResponseEntity<CreditCardResponseData> saveCreditCard(@RequestBody CreditCard cardData) {
        logger.info("Request Date : "+cardData);
        return creditCardValidationService.saveCreditCard(cardData);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable int id) {
        return creditCardValidationService.getCreditCard(id);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        return creditCardValidationService.getAllCreditCards();
    }

    @GetMapping(path = "/type/{cardType}")
    public ResponseEntity<List<CreditCard>> getAllCreditCardsByCardType(@PathVariable String cardType) {
        return creditCardValidationService.getAllCreditCardsByCardType(cardType);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteCreditCard(@PathVariable int id) {
        return creditCardValidationService.deleteCreditCard(id);
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllCreditCards() {
        return creditCardValidationService.deleteAllCreditCards();
    }

    @DeleteMapping(path = "/type/{cardType}")
    public ResponseEntity<HttpStatus> deleteCreditCardByCardType(@PathVariable String cardType) {
        return creditCardValidationService.deleteCreditCardByCardType(cardType);
    }
}
