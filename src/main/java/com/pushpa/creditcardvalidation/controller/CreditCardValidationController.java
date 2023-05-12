package com.pushpa.creditcardvalidation.controller;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.service.CreditCardValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<CreditCard> saveCreditCard(@RequestBody CreditCard cardData) {
        CreditCard savedCard = creditCardValidationService.saveCreditCard(cardData);
        return new ResponseEntity<>(savedCard, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CreditCard> getCreditCard(@PathVariable int id) {
        CreditCard getCard = creditCardValidationService.getCreditCard(id);
        return new ResponseEntity<>(getCard, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        List<CreditCard> getCardList = creditCardValidationService.getAllCreditCards();
        return new ResponseEntity<>(getCardList, HttpStatus.OK);
    }

    @GetMapping(path = "/type/{cardType}")
    public ResponseEntity<List<CreditCard>> getAllCreditCardsByCardType(@PathVariable String cardType) {
        List<CreditCard> getCardList = creditCardValidationService.getAllCreditCardsByCardType(cardType);
        return new ResponseEntity<>(getCardList, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCreditCard(@PathVariable int id) {
        creditCardValidationService.deleteCreditCard(id);
    }

    @DeleteMapping
    public void deleteAllCreditCards() {
        creditCardValidationService.deleteAllCreditCards();
    }

    @DeleteMapping(path = "/type/{cardType}")
    public void deleteCreditCardByCardType(@PathVariable String cardType) {
        creditCardValidationService.deleteCreditCardByCardType(cardType);
    }
}
