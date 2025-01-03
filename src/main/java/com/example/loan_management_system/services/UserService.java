package com.example.loan_management_system.services;

import com.example.loan_management_system.dtos.RequestResponse;
import com.example.loan_management_system.entities.OurUser;
import com.example.loan_management_system.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
    public class UserService {
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


        public OurUser updateUser(Integer id, RequestResponse requestResponse) {
            OurUser user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            user.setName(requestResponse.getName());
            user.setEmail(requestResponse.getEmail());
            user.setPhoneNumber(requestResponse.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(requestResponse.getPassword()));
            user.setRole(requestResponse.getRole());
            return userRepository.save(user);
        }

        public List<OurUser> getAllUsers() {
            return userRepository.findAll();
        }

        public Optional<OurUser> getUserById(Integer id) {
            return userRepository.findById(id);
        }

        public void deleteUser(Integer id) {
            userRepository.deleteById(id);
        }
    }