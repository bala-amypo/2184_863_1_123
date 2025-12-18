package com.example.demo.model;

import jakarta.persistence.*;

public class EmployeeSkill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proficiencyLevel;
    private Integer yearsOfExperience;
    private Boolean active = true;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getProficiencyLevel(){
        return proficiencyLevel;
    }
    public void setProficiencyLevel(String proficiencyLevel){
        this.proficiencyLevel = proficiencyLevel;
    }
    public Integer getYearsOfExperience(){
        return yearsOfExperience;
    }
    public void setYearsOfExperience(Integer yearsOfExperience){
        this.yearsOfExperience = yearsOfExperience;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active = active;
    }


}