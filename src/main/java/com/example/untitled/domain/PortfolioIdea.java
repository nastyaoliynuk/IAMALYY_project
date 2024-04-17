package com.example.untitled.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "portfolio_ideas")
public class PortfolioIdea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "idea_title")
    private String ideaTitle;

    @Column(name = "description")
    private String description;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @ElementCollection
    @CollectionTable(name = "idea_photos", joinColumns = @JoinColumn(name = "idea_id"))
    @Column(name = "photo_url")
    private List<String> photoUrls;

    // Конструктори, геттери та сеттери

    public PortfolioIdea() {
        this.publicationDate = LocalDate.now(); // Встановлення поточної дати при створенні нової ідеї
    }

    public PortfolioIdea(String username, String ideaTitle, String description, List<String> photoUrls) {
        this.username = username;
        this.ideaTitle = ideaTitle;
        this.description = description;
        this.publicationDate = LocalDate.now(); // Встановлення поточної дати при створенні нової ідеї
        this.photoUrls = photoUrls;
    }

    // Геттери та сеттери

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return username;
    }

    public void setUserId(String username) {
        this.username = username;
    }

    public String getIdeaTitle() {
        return ideaTitle;
    }

    public void setIdeaTitle(String ideaTitle) {
        this.ideaTitle = ideaTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }
}
