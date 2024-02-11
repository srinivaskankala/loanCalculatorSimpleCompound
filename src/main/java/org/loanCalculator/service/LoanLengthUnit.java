package org.loanCalculator.service;

import java.util.HashMap;
import java.util.Map;

public enum LoanLengthUnit {
    DAYS("DAYS"),
    MONTHS("MONTHS"),
    YEARS("YEARS");

    private static final Map<String, LoanLengthUnit> loanLengthUnitMap = new HashMap<>();

    static {
        for (LoanLengthUnit l : values()) {
            loanLengthUnitMap.put(l.name(), l);
        }
    }

    private final String text;

    LoanLengthUnit(final String s) {
        this.text = s;
    }


    @Override
    public String toString() {
        return text;
    }

}