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

    public LoanService(LoanRepo loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan applyForLoan(LoanDto loanDto) {
        Loan loan = new Loan();
        loan.setUserId(loanDto.getUserId());
        loan.setAmount(loanDto.getAmount());
        loan.setTenure(loanDto.getTenure());
        loan.setInterestRate(loanDto.getInterestRate());
        loan.setStatus(LoanStatus.PENDING);
        return loanRepository.save(loan);
    }

    public List<Loan> getLoansByUserId(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public Loan updateLoanStatus(Long loanId, LoanStatus status) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new RuntimeException("Loan not found"));
        loan.setStatus(status);
        return loanRepository.save(loan);
    }

    public BigDecimal calculateInterest(BigDecimal principal, BigDecimal rate, int timeInMonths) {
        BigDecimal timeInYears = BigDecimal.valueOf(timeInMonths).divide(BigDecimal.valueOf(12), 2, BigDecimal.ROUND_HALF_UP);
        return principal.multiply(rate).multiply(timeInYears).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_HALF_UP);
    }
}
