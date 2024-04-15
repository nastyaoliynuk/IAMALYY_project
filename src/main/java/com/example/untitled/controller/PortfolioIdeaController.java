package com.example.untitled.controller;


import com.example.untitled.domain.PortfolioIdea;
import com.example.untitled.repos.PortfolioIdeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ideas")
public class PortfolioIdeaController {

    private final PortfolioIdeaRepository portfolioIdeaRepository;

    @Autowired
    public PortfolioIdeaController(PortfolioIdeaRepository portfolioIdeaRepository) {
        this.portfolioIdeaRepository = portfolioIdeaRepository;
    }

    // Endpoint для створення нової ідеї
    @PostMapping("/create")
    public ResponseEntity<PortfolioIdea> createIdea(@RequestBody PortfolioIdea idea) {
        PortfolioIdea savedIdea = portfolioIdeaRepository.save(idea);
        return new ResponseEntity<>(savedIdea, HttpStatus.CREATED);
    }

    // Endpoint для отримання всіх ідей
    @GetMapping("/all")
    public ResponseEntity<List<PortfolioIdea>> getAllIdeas() {
        List<PortfolioIdea> ideas = portfolioIdeaRepository.findAll();
        return new ResponseEntity<>(ideas, HttpStatus.OK);
    }

    // Додаткові методи контролера можна додавати для отримання конкретних ідей, оновлення, видалення і т. д.
}

