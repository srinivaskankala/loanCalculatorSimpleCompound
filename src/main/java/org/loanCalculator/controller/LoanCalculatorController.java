package org.loanCalculator.controller;

import org.loanCalculator.exception.InvalidLoanCalculator;
import org.loanCalculator.service.SimpleLoanCalculatorServiceImpl;
import org.loanCalculator.service.LoanLengthUnit;
import org.loanCalculator.service.provider.InterestRateProvider;
import org.loanCalculator.service.provider.SimpleFixedInterestRateProvider;
import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("loan")
public class LoanCalculatorController {
    private final SimpleLoanCalculatorServiceImpl loanCalculatorServiceImpl;
    public InterestRateProvider interestRateProvider;

    public LoanCalculatorController(SimpleLoanCalculatorServiceImpl loanCalculatorServiceImpl) {
        this.loanCalculatorServiceImpl = loanCalculatorServiceImpl;
     }

    @GetMapping("/calculate-monthly-repayment")
    public double calculateMonthlyRepayment(@RequestParam String principal,
                                            @RequestParam String loanLength,
                                            @RequestParam String loanLengthUnit) {
        if (principal.equals("null")) {
            throw new InvalidLoanCalculator( "principal must not be null");
        }
        if (loanLength.equals("null")) {
            throw new InvalidLoanCalculator( " loanLength must not be null");
        }
        if (loanLengthUnit.equals( "null")) {
            throw new InvalidLoanCalculator( " loanLengthUnit must not be null");
        }
        try {
            interestRateProvider = new SimpleFixedInterestRateProvider();
       double pricipalDouble = Double.parseDouble(principal);
       double loanLengthDouble = Double.parseDouble(loanLength);
       LoanLengthUnit loanLengthUnit1 = LoanLengthUnit.valueOf(loanLengthUnit);
        return loanCalculatorServiceImpl.calculateMonthlyRepayment(pricipalDouble, loanLengthDouble, loanLengthUnit1, interestRateProvider);
    } catch (NumberFormatException e) {
          throw new InvalidLoanCalculator(principal + ",  " + loanLength
                  + "  :enter valid numbers for principal and loanLenght");
        } catch (IllegalArgumentException e) {
            throw new InvalidLoanCalculator(loanLengthUnit
                    + " :enter valid a LoanLengthUnit");
        }
    }


}
