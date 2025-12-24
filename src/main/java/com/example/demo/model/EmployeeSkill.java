package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "employee_skills",
       uniqueConstraints = @UniqueConstraint(columnNames = {"employee_id", "skill_id"}))
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String proficiencyLevel; // Beginner, Intermediate, Advanced, Expert

    @Min(0)
    private Integer yearsOfExperience;

    private Boolean active = true;

    private static final List<String> VALID_LEVELS = Arrays.asList("Beginner", "Intermediate", "Advanced", "Expert");

    // Constructors
    public EmployeeSkill() {}

    public EmployeeSkill(Employee employee, Skill skill, String proficiencyLevel, Integer yearsOfExperience) {
        this.setEmployee(employee);
        this.setSkill(skill);
        this.setProficiencyLevel(proficiencyLevel);
        this.setYearsOfExperience(yearsOfExperience);
        this.active = true;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) {
        if (employee == null || !Boolean.TRUE.equals(employee.getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }
        this.employee = employee;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) {
        if (skill == null || !Boolean.TRUE.equals(skill.getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }
        this.skill = skill;
    }

    public String getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(String proficiencyLevel) {
        if (!VALID_LEVELS.contains(proficiencyLevel)) {
            throw new IllegalArgumentException("Invalid proficiency");
        }
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) {
        if (yearsOfExperience == null || yearsOfExperience < 0) {
            throw new IllegalArgumentException("Experience years");
        }
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
