package com.example.demo.dto;

import java.util.List;

public class EmployeeSearchRequest {
    private List<String> skills;

    public EmployeeSearchRequest() {}

    public EmployeeSearchRequest(List<String> skills) {
        if (skills == null || skills.isEmpty()) throw new IllegalArgumentException("must not be empty");
        this.skills = skills;
    }

    public List<String> getSkills() { return skills; }

    public void setSkills(List<String> skills) {
        if (skills == null || skills.isEmpty()) throw new IllegalArgumentException("must not be empty");
        this.skills = skills;
    }
}
