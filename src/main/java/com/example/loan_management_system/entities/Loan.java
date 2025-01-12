package com.example.loan_management_system.entities;

import com.example.loan_management_system.enums.LoanStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Loan amount is required")
    private BigDecimal amount;

    @NotNull(message = "Loan tenure is required")
    private Integer tenure; // in months

    @NotNull(message = "Interest rate is required")
    private BigDecimal interestRate; // in percentage

    @Enumerated(EnumType.STRING)
    private LoanStatus status; // PENDING, APPROVED, REJECTED
}
