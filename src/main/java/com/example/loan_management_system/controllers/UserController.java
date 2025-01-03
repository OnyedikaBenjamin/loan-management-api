package com.example.loan_management_system.controllers;

import com.example.loan_management_system.dtos.RequestResponse;
import com.example.loan_management_system.entities.OurUser;
import com.example.loan_management_system.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<OurUser> updateUser(@PathVariable Integer id, @Validated @RequestBody RequestResponse requestResponse) {
        return ResponseEntity.ok(userService.updateUser(id, requestResponse));
    }

    @GetMapping
    public ResponseEntity<List<OurUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OurUser> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}