package com.example.loan_management_system.entities;

import com.example.loan_management_system.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
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
    private TransactionType type; // DISBURSEMENT, REPAYMENT

    private LocalDateTime timestamp;
}