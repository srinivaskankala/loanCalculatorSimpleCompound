package org.loanCalculator.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loanCalculator.service.provider.InterestRateProvider;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class LoanCalculatorServiceTests {

    @Mock
    private InterestRateProvider interestRateProvider;
    private LoanCalculatorService loanCalculatorService;

    @BeforeEach
    public void setUp() {
        Mockito.when(interestRateProvider.getInterestRate()).thenReturn(0.093);
        loanCalculatorService = new SimpleLoanCalculatorServiceImpl();

    }


    @Test
    void calculateMonthlyRepaymentMonthsUnit() {
        double principal = 10000;
        double loanLength = 12;
        LoanLengthUnit loanLengthUnit = LoanLengthUnit.MONTHS;

        double expectedMonthlyRepayment = 910.8333333333334;
        double actualMonthlyRepayment = loanCalculatorService.calculateMonthlyRepayment(principal, loanLength, loanLengthUnit, interestRateProvider);

        assertEquals(expectedMonthlyRepayment, actualMonthlyRepayment, 0.0001);
    }

    @Test
    void calculateMonthlyRepaymentYearsUnit() {
        double principal = 10000;
        int loanLength = 1;
        LoanLengthUnit loanLengthUnit = LoanLengthUnit.YEARS;

        double expectedMonthlyRepayment = 910.8333333333334;
        double actualMonthlyRepayment = loanCalculatorService.calculateMonthlyRepayment(principal, loanLength, loanLengthUnit, interestRateProvider);

        assertEquals(expectedMonthlyRepayment, actualMonthlyRepayment, 0.0001);
    }

}