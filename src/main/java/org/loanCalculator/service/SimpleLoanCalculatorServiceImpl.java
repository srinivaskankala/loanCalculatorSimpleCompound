package org.loanCalculator.service;

import org.loanCalculator.service.provider.InterestRateProvider;
import org.loanCalculator.utility.SimpleLoanCalculatorUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SimpleLoanCalculatorServiceImpl implements LoanCalculatorService {
    @Override
    public double calculateMonthlyRepayment(double principal, double loanLength,
                                            LoanLengthUnit loanLengthUnit,
                                            InterestRateProvider interestRateProvider) {
        int loanLegth = SimpleLoanCalculatorUtil.calculateMonthlyRepayment(loanLength, loanLengthUnit);
        double monthlyRepayment = principal *
                (1 + interestRateProvider.getInterestRate()) / loanLegth;
        return monthlyRepayment;
    }
}