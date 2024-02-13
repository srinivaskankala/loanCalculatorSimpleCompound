package org.loanCalculator.service;

import org.loanCalculator.service.provider.InterestRateProvider;

public interface LoanCalculatorService {



    double calculateMonthlyRepayment (double principal, double loanLength,
                                      LoanLengthUnit loanLengthUnit,
                                      InterestRateProvider interestRateProvider);
}
