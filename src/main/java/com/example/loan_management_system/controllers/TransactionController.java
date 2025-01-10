package com.example.loan_management_system.controllers;

import com.example.loan_management_system.dtos.LoanDto;
import com.example.loan_management_system.dtos.TransactionDTO;
import com.example.loan_management_system.entities.Loan;
import com.example.loan_management_system.entities.Transaction;
import com.example.loan_management_system.enums.TransactionType;
import com.example.loan_management_system.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("auth/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> recordTransaction(@RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.ok(transactionService.recordTransaction(transactionDTO));
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getTransactionsByUserId(userId));
    }

    @GetMapping("/loan/{loanId}")
    public ResponseEntity<List<Transaction>> getTransactionsByLoanId(@PathVariable Long loanId) {
        return ResponseEntity.ok(transactionService.getTransactionsByLoanId(loanId));
    }

    @GetMapping("/user/{userId}/statement")
    public ResponseEntity<String> getTransactionStatement(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.generateTransactionStatement(userId));
    }

}