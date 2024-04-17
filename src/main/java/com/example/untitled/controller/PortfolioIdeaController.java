package com.example.untitled.controller;


import com.example.untitled.domain.PortfolioIdea;
import com.example.untitled.repos.PortfolioIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.untitled.repos.UserRepository;


import java.util.List;

@RestController
@CrossOrigin(origins = "http://192.168.0.186:4200")
@RequestMapping("/api/ideas")
public class PortfolioIdeaController {

    private final PortfolioIdeaRepository portfolioIdeaRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PortfolioIdeaController(PortfolioIdeaRepository portfolioIdeaRepository) {
        this.portfolioIdeaRepository = portfolioIdeaRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createIdea(@RequestBody PortfolioIdea idea) {
        if (!userRepository.existsByUsername(idea.getUserId())) {
            return ResponseEntity.badRequest().body("User not found"); // Повертаємо помилку, якщо користувач не існує
        }
        PortfolioIdea savedIdea = portfolioIdeaRepository.save(idea);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedIdea);
    }


    @GetMapping("/all")
    public ResponseEntity<List<PortfolioIdea>> getAllIdeas() {
        List<PortfolioIdea> ideas = portfolioIdeaRepository.findAll();
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

}

