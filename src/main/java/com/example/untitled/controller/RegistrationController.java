package com.example.untitled.controller;

import com.example.untitled.domain.User;
import com.example.untitled.dto.UserRegistrationDTO;
import com.example.untitled.repos.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationDTO newUserDTO) {
        if (userRepository.existsByUsername(newUserDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        User newUser = new User();
        newUser.setUsername(newUserDTO.getUsername());
        newUser.setPassword(newUserDTO.getPassword());
        userRepository.save(newUser);

        return ResponseEntity.ok("User registered successfully");
    }
}
