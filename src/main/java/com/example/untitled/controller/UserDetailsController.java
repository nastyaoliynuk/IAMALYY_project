package com.example.untitled.controller;

import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import com.google.i18n.phonenumbers.NumberParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")

@RequestMapping("/profile")
public class UserDetailsController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/update")
    public ResponseEntity<String> updateProfile(@RequestBody User updatedUser) {
        User existingUser = userRepository.findByUsernameAndPassword(updatedUser.getUsername(), updatedUser.getPassword());
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Оновлення інформації про користувача
        try {
            existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
        } catch (NumberParseException e) {
            return ResponseEntity.badRequest().body("Error format phone number.");
        }
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setInfo_about_me(updatedUser.getInfo_about_me());
        existingUser.setLocation(updatedUser.getLocation());
        existingUser.setAvatar(updatedUser.getAvatar());
        existingUser.setStatus(updatedUser.getAvatar());

        userRepository.save(existingUser);

        return ResponseEntity.ok("Profile updated successfully");
    }}

   /* @GetMapping("/get/{username}")
    public ResponseEntity<User> getProfile(@PathVariable String username) {
        // Пошук користувача за ім'ям
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(user);
    }*/


