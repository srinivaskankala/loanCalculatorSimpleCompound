package org.loanCalculator.utility;

import org.loanCalculator.service.LoanLengthUnit;


public class SimpleLoanCalculatorUtil {

    public static int calculateMonthlyRepayment(double loanLength,
                                                LoanLengthUnit loanLengthUnit) {
        switch (loanLengthUnit) {
            case DAYS:
                loanLength = (int) Math.ceil(loanLength);
                return (int) Math.ceil(loanLength / 30.0);
            case YEARS:
                return (int) Math.ceil(loanLength * 12);
            default:
                return (int) Math.ceil(loanLength);
        }

    }
}


