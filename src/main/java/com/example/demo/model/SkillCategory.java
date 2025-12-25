package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "skill_categories",
       uniqueConstraints = @UniqueConstraint(columnNames = "categoryName"))
public class SkillCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String categoryName;

    private String description;
    private Boolean active = true;

    // Constructors
    public SkillCategory() {}

    public SkillCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
