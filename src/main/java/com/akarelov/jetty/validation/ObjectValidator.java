package com.akarelov.jetty.validation;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ObjectValidator {
    private final static String WRONG_NUMBER_FORMAT = "you passed not number format";

    public int parseStringToInt(String number) {
        if (number.isEmpty()) {
            throw new NumberFormatException(WRONG_NUMBER_FORMAT);
        }
        try {
            return Integer.parseInt(number);
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(WRONG_NUMBER_FORMAT);
        }
    }
}
