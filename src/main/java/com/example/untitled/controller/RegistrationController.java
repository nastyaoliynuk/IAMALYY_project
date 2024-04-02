package com.example.untitled.controller;

import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")

public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User newUser) throws NumberParseException {
        // Перевірити, чи існує користувач з таким самим ім'ям користувача
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }

        // Перевірити, чи поле паролю не є порожнім
        if (newUser.getPassword() == null || newUser.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("Password field cannot be empty");
        }

        // Якщо користувача з таким ім'ям користувача не існує і поле паролю не є порожнім,
        // зберегти нового користувача в базу даних
        userRepository.save(newUser);
        return ResponseEntity.ok("User registered successfully");
    }

}
