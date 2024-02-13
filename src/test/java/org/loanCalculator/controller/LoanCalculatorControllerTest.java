package org.loanCalculator.controller;

import org.loanCalculator.service.SimpleLoanCalculatorServiceImpl;
import org.loanCalculator.service.LoanLengthUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loanCalculator.service.provider.SimpleFixedInterestRateProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LoanCalculatorController.class)
public class LoanCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleLoanCalculatorServiceImpl simpleLoanCalculatorServiceImpl;
    @MockBean
    private SimpleFixedInterestRateProvider interestRateProvider;


    @Test
    void calculateMonthlyRepayment_shouldReturnCorrectResponse() throws Exception {
        double principal = 10000;
        double loanLength = 12;
        LoanLengthUnit loanLengthUnit = LoanLengthUnit.MONTHS;
        double expectedMonthlyRepayment = 910.8333333333334;
        when(simpleLoanCalculatorServiceImpl.calculateMonthlyRepayment(principal, loanLength, loanLengthUnit, interestRateProvider))
                .thenReturn(expectedMonthlyRepayment);
        mockMvc.perform(MockMvcRequestBuilders.get("/loan/calculateMonthlyRepayment")
                        .param("principal", String.valueOf(principal))
                        .param("loanLength", String.valueOf(loanLength))
                        .param("loanLengthUnit", loanLengthUnit.name())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(Double.toString(expectedMonthlyRepayment)));

    }

    @Test
    void calculateMonthlyRepayment_shouldHandleInvalidParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/loan/calculateMonthlyRepayment")
                        .param("loanLength", String.valueOf(12))
                        .param("loanLengthUnit", LoanLengthUnit.MONTHS.name())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/calculateMonthlyRepayment")
                        .param("principal", String.valueOf(10000))
                        .param("loanLengthUnit", LoanLengthUnit.MONTHS.name())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/calculateMonthlyRepayment")
                        .param("principal", String.valueOf(10000))
                        .param("loanLength", String.valueOf(12))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.get("/loan/calculateMonthlyRepayment")
                        .param("principal", String.valueOf(10000))
                        .param("loanLength", String.valueOf(12))
                        .param("loanLengthUnit", "INVALID_UNIT")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
