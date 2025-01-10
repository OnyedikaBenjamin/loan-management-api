package com.example.loan_management_system.entities;

import com.example.loan_management_system.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    public Transaction(Long userId, Long loanId, BigDecimal amount, TransactionType type, LocalDateTime timestamp) {
        this.userId = userId;
        this.loanId = loanId;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Loan ID is required")
    private Long loanId;

    @NotNull(message = "Transaction amount is required")
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime timestamp;
}