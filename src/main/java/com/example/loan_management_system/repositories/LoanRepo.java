package com.example.loan_management_system.repositories;

import com.example.loan_management_system.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LoanRepo extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long userId);
}