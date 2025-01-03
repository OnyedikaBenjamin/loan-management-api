package com.example.loan_management_system.repositories;

import com.example.loan_management_system.entities.OurUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<OurUser, Integer> {
    Optional<OurUser> findByEmail(String email);
    Optional<OurUser> findByPhoneNumber(String phoneNumber);
}
