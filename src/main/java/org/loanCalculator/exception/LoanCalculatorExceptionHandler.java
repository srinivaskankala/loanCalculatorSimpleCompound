package org.loanCalculator.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class LoanCalculatorExceptionHandler {

    @ExceptionHandler(InvalidLoanCalculator.class)
    public ResponseEntity<ErrorResponse> handleInvalidLoanCalculator(InvalidLoanCalculator ex) {
        return ResponseEntity.badRequest().body(new ErrorResponse(ex.getMessage()));
    }
}
