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

    public EmployeeSkill() {}

    public EmployeeSkill(Employee employee, Skill skill, String proficiencyLevel, Integer yearsOfExperience, Boolean active) {
        if (!employee.getActive()) throw new IllegalArgumentException("inactive employee");
        if (!skill.getActive()) throw new IllegalArgumentException("inactive skill");
        if (yearsOfExperience < 0) throw new IllegalArgumentException("Experience years");
        if (!isValidProficiency(proficiencyLevel)) throw new IllegalArgumentException("Invalid proficiency");

        this.employee = employee;
        this.skill = skill;
        this.proficiencyLevel = proficiencyLevel;
        this.yearsOfExperience = yearsOfExperience;
        this.active = active;
    }

    private boolean isValidProficiency(String level) {
        return level != null && (level.equals("Beginner") || level.equals("Intermediate") || level.equals("Advanced") || level.equals("Expert"));
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) {
        if (!employee.getActive()) throw new IllegalArgumentException("inactive employee");
        this.employee = employee;
    }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) {
        if (!skill.getActive()) throw new IllegalArgumentException("inactive skill");
        this.skill = skill;
    }

    public String getProficiencyLevel() { return proficiencyLevel; }
    public void setProficiencyLevel(String proficiencyLevel) {
        if (!isValidProficiency(proficiencyLevel)) throw new IllegalArgumentException("Invalid proficiency");
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Integer yearsOfExperience) {
        if (yearsOfExperience < 0) throw new IllegalArgumentException("Experience years");
        this.yearsOfExperience = yearsOfExperience;
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
