package com.pushpa.creditcardvalidation.util;


public class CommonLogics {

    public static boolean validateCreditCard(String creditCardNumber, String cardType) {
        boolean isValid = false;
        if (creditCardNumber != null && !creditCardNumber.isEmpty()) {
            int cardNumberLength = creditCardNumber.trim().length();
            if (cardNumberLength >= Constants.CREDIT_CARD_MIN_LENGTH && cardNumberLength <= Constants.CREDIT_CARD_MAX_LENGTH) {
                if (!Constants.INVALID.equals(cardType))
                    isValid = true;
            }
        }
        return isValid;
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
