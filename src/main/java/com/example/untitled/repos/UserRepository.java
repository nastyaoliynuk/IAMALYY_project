package com.example.untitled.repos;

import com.example.untitled.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Query("")
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username); // Метод для пошуку користувача за ім'ям користувача
    User findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username); // Метод для перевірки наявності користувача за ім'ям користувача

}
