package org.loanCalculator.service;

import java.util.HashMap;
import java.util.Map;

public enum LoanLengthUnit {
    DAYS("DAYS"),
    MONTHS("MONTHS"),
    YEARS("YEARS");


    private final String text;

    LoanLengthUnit(final String s) {
        this.text = s;
    }


    @Override
    public String toString() {
        return text;
    }

}