package com.example.demo.dto;

import java.util.List;

public class EmployeeSearchRequest {

    private List<String> skills;

    // Constructors
    public EmployeeSearchRequest() {
    }

    public EmployeeSearchRequest(List<String> skills) {
        this.skills = skills;
    }

    // Getters & Setters
    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
