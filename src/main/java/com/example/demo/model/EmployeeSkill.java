package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_skills")
public class EmployeeSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(optional = false)
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String proficiencyLevel;
    private Integer yearsOfExperience;
    private Boolean active = true;

    // Constructors
    public EmployeeSkill() {}

    public EmployeeSkill(Employee employee, Skill skill,
                         String proficiencyLevel, Integer yearsOfExperience) {
        this.employee = employee;
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        validate();
    }

    // Validation
    public void validate() {
        if (yearsOfExperience == null || yearsOfExperience < 0) {
            throw new IllegalArgumentException("Experience years");
        }

        if (!proficiencyLevel.equals("Beginner") &&
            !proficiencyLevel.equals("Intermediate") &&
            !proficiencyLevel.equals("Advanced") &&
            !proficiencyLevel.equals("Expert")) {
            throw new IllegalArgumentException("Invalid proficiency");
        }

        if (employee != null && Boolean.FALSE.equals(employee.getActive())) {
            throw new IllegalArgumentException("inactive employee");
        }

        if (skill != null && Boolean.FALSE.equals(skill.getActive())) {
            throw new IllegalArgumentException("inactive skill");
        }
    }

    // Getters & Setters
    public Long getId() { return id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public String getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(String proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
