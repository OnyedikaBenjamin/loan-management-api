package com.example.loan_management_system.controllers;

import com.example.loan_management_system.dtos.RequestResponse;
import com.example.loan_management_system.entities.OurUser;
import com.example.loan_management_system.services.AuthService;
import com.example.loan_management_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/auth/user")
public class UserController {
    @Autowired private AuthService authService;
    @Autowired private UserService userService;


    @GetMapping("/all")
    public ResponseEntity<List<OurUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/{id}")
    public ResponseEntity<OurUser> updateUser(@PathVariable Integer id, @Validated @RequestBody RequestResponse requestResponse) {
        return ResponseEntity.ok(userService.updateUser(id, requestResponse));
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

    @PostMapping("/signup")
    public ResponseEntity <RequestResponse> signUp(@RequestBody RequestResponse signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity <RequestResponse> signIn(@RequestBody RequestResponse signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity <RequestResponse> refreshToken(@RequestBody RequestResponse refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}