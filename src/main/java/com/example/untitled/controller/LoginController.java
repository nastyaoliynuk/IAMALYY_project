package com.example.untitled.controller;

import com.example.untitled.domain.User;
import com.example.untitled.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")

public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(@RequestBody User request) {
        // Отримати дані про користувача з бази даних за допомогою репозиторія
        User user = userRepository.findByUsername(request.getUsername());

        // Перевірити, чи знайдено користувача та чи співпадає його пароль
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return "Login successful"; // Повернути успішне повідомлення
        } else {
            return "Invalid username or password"; // Повернути повідомлення про невірний логін або пароль
        }
    }
}

