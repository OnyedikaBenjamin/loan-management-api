package com.example.loan_management_system.controllers;

import com.example.loan_management_system.enums.LoanStatus;
import com.example.loan_management_system.dtos.LoanDto;
import com.example.loan_management_system.entities.Loan;
import com.example.loan_management_system.services.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public ResponseEntity<Loan> applyForLoan(@Validated @RequestBody LoanDto loanDto) {
        return ResponseEntity.ok(loanService.applyForLoan(loanDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoansByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(loanService.getLoansByUserId(userId));
    }

    @PatchMapping("/{loanId}/status")
    public ResponseEntity<Loan> updateLoanStatus(@PathVariable Long loanId, @RequestParam LoanStatus status) {
        return ResponseEntity.ok(loanService.updateLoanStatus(loanId, status));
    }

    @GetMapping("/calculate-interest")
    public ResponseEntity<BigDecimal> calculateInterest(
            @RequestParam BigDecimal principal,
            @RequestParam BigDecimal rate,
            @RequestParam int timeInMonths) {
        return ResponseEntity.ok(loanService.calculateInterest(principal, rate, timeInMonths));
    }
}