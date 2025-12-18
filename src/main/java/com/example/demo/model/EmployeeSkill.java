package com.example.demo.model;

public class EmployeeSkill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proficiencyLevel;
    private Integer yearOfExperience;
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
    public void ProficiencyLevel(String category){
        this.category = category;
    }



}