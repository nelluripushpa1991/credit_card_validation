package com.pushpa.creditcardvalidation.util;

import com.pushpa.creditcardvalidation.entity.CreditCard;
import com.pushpa.creditcardvalidation.model.CreditCardResponseData;

public class CommonLogics {
    public static CreditCardResponseData getResponseData(CreditCard creditCard) {
        CreditCardResponseData creditCardResponseData = new CreditCardResponseData();
        int cardNumberLength = creditCard.getCreditCardNumber().length();
        String creditCardNumber = creditCard.getCreditCardNumber().trim();
        if (cardNumberLength >= Constants.CREDIT_CARD_MIN_LENGTH && cardNumberLength <= Constants.CREDIT_CARD_MAX_LENGTH) {
            // getting cardType based number series
            creditCardResponseData.setCardType(CommonLogics.getCardType(creditCard.getCreditCardNumber()));
            if (creditCardResponseData.getCardType().equals(Constants.INVALID))
                creditCardResponseData.setMessage(Constants.INVALID_INPUT);
            else
                creditCardResponseData.setMessage(Constants.VALID);
        } else {
            creditCardResponseData.setCardType(CommonLogics.getCardType(creditCard.getCreditCardNumber()));
            creditCardResponseData.setMessage(Constants.INVALID);
        }
        return creditCardResponseData;
    }

    public static String getCardType(String creditCardNumber) {
        String cardType = "";
        if (creditCardNumber.startsWith(Constants.VISA_SERIES)) {
            cardType = Constants.VISA;
        } else if (creditCardNumber.startsWith(Constants.MASTER_CARD_SERIES)) {
            cardType = Constants.MASTER_CARD;
        } else if (creditCardNumber.startsWith(Constants.AMERICAN_EXPRESS_SERIES)) {
            cardType = Constants.AMERICAN_EXPRESS;
        } else {
            cardType = Constants.INVALID;
        }
        return cardType;
    }
}
