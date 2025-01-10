package com.example.loan_management_system.services;

import com.example.loan_management_system.enums.LoanStatus;
import com.example.loan_management_system.dtos.LoanDto;
import com.example.loan_management_system.entities.Loan;
import com.example.loan_management_system.repositories.LoanRepo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class LoanService {
    private final LoanRepo loanRepository;
    private final AuditService auditLogService;

    public LoanService(LoanRepo loanRepository, AuditService auditLogService) {
        this.loanRepository = loanRepository;
        this.auditLogService = auditLogService;
    }

    public Loan applyForLoan(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setUserId(loanDto.getUserId());
        loan.setAmount(loanDto.getAmount());
        loan.setTenure(loanDto.getTenure());
        loan.setInterestRate(loanDto.getInterestRate());
        loan.setStatus(LoanStatus.PENDING);

        Loan savedLoan = loanRepository.save(loan);

        auditLogService.logAction(
                "Loan Application",
                String.format("User %d applied for a loan of amount %s with tenure %d months.",
                        loanDto.getUserId(), loanDto.getAmount(), loanDto.getTenure()),
                "Loan",
                loanDto.getUserId()
        );

        return savedLoan;
    }

    public List<Loan> getLoansByUserId(Long userId) {
        List<Loan> loans = loanRepository.findByUserId(userId);

        // Log the loan retrieval action
        auditLogService.logAction(
                "Fetch Loans",
                String.format("Fetched loans for User %d. Total loans retrieved: %d", userId, loans.size()),
                "Loan",
                userId
        );

        return loans;
    }

    public Loan updateLoanStatus(Long loanId, LoanStatus status) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));

        LoanStatus previousStatus = loan.getStatus();
        loan.setStatus(status);

        Loan updatedLoan = loanRepository.save(loan);


        auditLogService.logAction(
                "Update Loan Status",
                String.format("Loan %d status updated from %s to %s.", loanId, previousStatus, status),
                "Loan",
                loan.getUserId()
        );

        return updatedLoan;
    }

    public BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, int timeInMonths) {
        BigDecimal timeInYears = BigDecimal.valueOf(timeInMonths).divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal interest = principal.multiply(rate).multiply(timeInYears).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);


        auditLogService.logAction(
                "Calculate Interest",
                String.format("Calculated interest for principal: %s, rate: %s%%, time: %d months. Interest: %s.",
                        principal, rate, timeInMonths, interest),
                "Loan",
                null
        );

        return interest;
    }
}