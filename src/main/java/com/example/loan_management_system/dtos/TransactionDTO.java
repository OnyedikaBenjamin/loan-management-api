package com.example.loan_management_system.dtos;
import com.example.loan_management_system.enums.TransactionType;

import java.math.BigDecimal;

public class TransactionDTO {
    private Long userId;
    private Long loanId;
    private BigDecimal amount;
    private TransactionType type;

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}