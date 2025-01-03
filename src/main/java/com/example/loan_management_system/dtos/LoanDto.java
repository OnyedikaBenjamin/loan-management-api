package com.example.loan_management_system.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoanDto {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Loan amount is required")
    private BigDecimal amount;

    @NotNull(message = "Loan tenure is required")
    private Integer tenure; // in months

    @NotNull(message = "Interest rate is required")
    private BigDecimal interestRate; // in percentage
}
