package com.example.loan_management_system.services;

import com.example.loan_management_system.entities.Transaction;
import com.example.loan_management_system.enums.TransactionType;
import com.example.loan_management_system.repositories.TransactionRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepo transactionRepository;

    public TransactionService(TransactionRepo transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction recordTransaction(Long userId, Long loanId, BigDecimal amount, TransactionType type) {
        validateTransactionAmount(amount, type);

        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setLoanId(loanId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTimestamp(LocalDateTime.now());
        return transactionRepository.save(transaction);
    }

    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByLoanId(Long loanId) {
        return transactionRepository.findByLoanId(loanId);
    }

    private void validateTransactionAmount(BigDecimal amount, TransactionType type) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero");
        }

        if (type == TransactionType.REPAYMENT && amount.scale() > 2) {
            throw new IllegalArgumentException("Repayment amount must have at most two decimal places");
        }
    }

    public String generateTransactionStatement(Long userId) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        StringBuilder statement = new StringBuilder();
        statement.append("Transaction Statement for User ID: ").append(userId).append("\n\n");

        for (Transaction transaction : transactions) {
            statement.append("Date: ").append(transaction.getTimestamp())
                    .append(", Loan ID: ").append(transaction.getLoanId())
                    .append(", Amount: ").append(transaction.getAmount())
                    .append(", Type: ").append(transaction.getType())
                    .append("\n");
        }

        return statement.toString();
    }

}