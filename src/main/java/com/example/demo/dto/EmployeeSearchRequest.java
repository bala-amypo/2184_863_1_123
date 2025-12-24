package com.example.demo.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;

public class EmployeeSearchRequest {

    @NotEmpty(message = "Skills list must not be empty")
    private List<String> skills;

    private Integer minProficiency; // optional
    private String department;       // optional

    public EmployeeSearchRequest() {
    }

    public EmployeeSearchRequest(List<String> skills, Integer minProficiency, String department) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.skills = skills;
        this.minProficiency = minProficiency;
        this.department = department;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        if (skills == null || skills.isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.skills = skills;
    }

    public Integer getMinProficiency() {
        return minProficiency;
    }

    public void setMinProficiency(Integer minProficiency) {
        this.minProficiency = minProficiency;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
