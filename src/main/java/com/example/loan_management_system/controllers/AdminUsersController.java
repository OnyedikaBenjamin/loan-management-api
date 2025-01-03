package com.example.loan_management_system.controllers;

import com.example.loan_management_system.dtos.RequestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsersController {



//    @GetMapping("/public/product")
//    public ResponseEntity<Object> getAllProduct(){
//        return ResponseEntity.ok(productRepo.findAll());
//    }

//    @PostMapping("/admin/saveproduct")
//    public ResponseEntity<Object> signUp(@RequestBody RequestResponse productRequest){
//        Product productToSave = new Product();
//        productToSave.setName(productRequest.getName());
//        return ResponseEntity.ok(productRepo.save(productToSave));
//    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone(){
        return ResponseEntity.ok("USERS alone can access this endpoint");
    }
    @GetMapping("/admin/user")
    public ResponseEntity<Object> adminAndUserApi(){
        return ResponseEntity.ok("ADMIN and USER can access this API");
    }
}
