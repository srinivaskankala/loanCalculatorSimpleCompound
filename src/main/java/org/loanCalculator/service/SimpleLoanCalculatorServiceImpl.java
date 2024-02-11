package org.loanCalculator.service;

import org.loanCalculator.exception.InvalidLoanCalculator;
import org.loanCalculator.service.provider.InterestRateProvider;
import org.springframework.stereotype.Service;

@Service
public class SimpleLoanCalculatorServiceImpl implements SimpleLoanCalculatorService {
  //  private static final double FIXED_INTEREST_RATE = 0.093;

    @Override
    public double calculateMonthlyRepayment(double principal, double loanLength, LoanLengthUnit loanLengthUnit, InterestRateProvider interestRateProvider) {
        if (principal <= 0 || loanLength <= 0) {
            throw new InvalidLoanCalculator("enter valid details : principal > 0, loanLength > 0, loanLengthUnit should be any one of them DAYS,MONTHS,YEARS");
        }
        if (loanLengthUnit == LoanLengthUnit.DAYS) {
            loanLength = (int) Math.ceil(loanLength);
            loanLength = (int) Math.ceil(loanLength / 30.0);
        } else if (loanLengthUnit == LoanLengthUnit.YEARS) {
            loanLength *= 12;
            loanLength = (int) Math.ceil(loanLength);
        } else {
            loanLength = (int) Math.ceil(loanLength);
        }
        double monthlyRepayment = principal * (1 + interestRateProvider.getInterestRate()) / loanLength;

        return monthlyRepayment;
    }

}
