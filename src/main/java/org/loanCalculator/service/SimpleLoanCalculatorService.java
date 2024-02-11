package org.loanCalculator.service;

import org.loanCalculator.service.provider.InterestRateProvider;

public interface SimpleLoanCalculatorService {

    double calculateMonthlyRepayment (double principal, double loanLength, LoanLengthUnit loanLengthUnit, InterestRateProvider interestRateProvider);
}
