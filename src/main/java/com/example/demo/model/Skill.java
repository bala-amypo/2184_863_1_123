package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "skills",
       uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String category;
    private String description;
    private Boolean active = true;

    @OneToMany(mappedBy = "skill")
    private List<EmployeeSkill> employeeSkills;

    // Constructors
    public Skill() {}

    public Skill(String name, String category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    // Getters & Setters
    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
