package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "skills", uniqueConstraints = @UniqueConstraint(columnNames = "skillName"))
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String skillCode;

    @NotBlank
    @Column(unique = true)
    private String skillName;

    @NotBlank
    private String category; // Technical / Functional

    // Constructors
    public Skill() {}

    public Skill(String skillCode, String skillName, String category) {
        this.skillCode = skillCode;
        this.skillName = skillName;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSkillCode() { return skillCode; }
    public void setSkillCode(String skillCode) { this.skillCode = skillCode; }

    public String getSkillName() { return skillName; }
    public void setSkillName(String skillName) { this.skillName = skillName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
