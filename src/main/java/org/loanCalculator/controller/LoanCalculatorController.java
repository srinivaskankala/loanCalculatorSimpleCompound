package org.loanCalculator.controller;

import org.loanCalculator.exception.InvalidLoanCalculator;
import org.loanCalculator.service.LoanCalculatorService;
import org.loanCalculator.service.LoanLengthUnit;
import org.loanCalculator.service.provider.InterestRateProvider;
import org.loanCalculator.service.provider.SimpleFixedInterestRateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("loan")
public class LoanCalculatorController {
    private LoanCalculatorService loanCalculatorService;
    public InterestRateProvider simpleInterestRateProvider;

    @Autowired
    public LoanCalculatorController(LoanCalculatorService loanService,
                                    InterestRateProvider interestRateProvider) {
        this.loanCalculatorService = loanService;
        this.simpleInterestRateProvider = interestRateProvider;
     }

    @GetMapping("/calculateMonthlyRepayment")
    public double calculateMonthlyRepayment(@RequestParam String principal,
                                            @RequestParam String loanLength,
                                            @RequestParam String loanLengthUnit) {
        if (principal.equals("null") || loanLength.equals("null")
                || loanLengthUnit.equals( "null")) {
            throw new InvalidLoanCalculator( "principal, loanLength, " +
                    "loanLengthUnit must not be null");
        }
        try {
       double pricipalDouble = Double.parseDouble(principal);
       double loanLengthDouble = Double.parseDouble(loanLength);
       LoanLengthUnit loanLengthUnit1 = LoanLengthUnit.valueOf(loanLengthUnit);
            if (pricipalDouble <= 0 || loanLengthDouble <= 0) {
                throw new InvalidLoanCalculator("enter valid details :" +
                        " principal > 0, loanLength > 0, loanLengthUnit should be any one of " +
                        "them DAYS,MONTHS,YEARS");
            }
        return loanCalculatorService.calculateMonthlyRepayment(pricipalDouble,
                loanLengthDouble, loanLengthUnit1, simpleInterestRateProvider);
    } catch (NumberFormatException e) {
          throw new InvalidLoanCalculator(principal + ",  " + loanLength
                  + "  :enter valid numbers for principal and loanLenght");
        } catch (IllegalArgumentException e) {
            throw new InvalidLoanCalculator(loanLengthUnit
                    + " :enter valid a LoanLengthUnit");
        }
    }


}
