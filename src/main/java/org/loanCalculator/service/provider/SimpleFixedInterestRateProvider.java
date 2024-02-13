package org.loanCalculator.service.provider;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
@NoArgsConstructor
public class SimpleFixedInterestRateProvider implements InterestRateProvider{


    @Value("${loanCalculator.simpleInterestRate}")
    private double rate;
    @Override
    public double getInterestRate() {
        return rate;
    }
}
