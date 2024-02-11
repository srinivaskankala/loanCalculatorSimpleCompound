package org.loanCalculator.service.provider;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@NoArgsConstructor
public class SimpleFixedInterestRateProvider implements InterestRateProvider{

    private static final double rate = 0.093;
    @Override
    public double getInterestRate() {
        return rate;
    }
}
