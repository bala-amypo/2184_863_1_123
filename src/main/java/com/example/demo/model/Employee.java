package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    private String department;
    private String jobTitle;
    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> employeeSkills;

    // Constructors
    public Employee() {
    }

    public Employee(Long id, String fullName, String email, String department,
                    String jobTitle, Boolean active) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.jobTitle = jobTitle;
        this.active = active;
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public List<EmployeeSkill> getEmployeeSkills() { return employeeSkills; }
    public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) {
        this.employeeSkills = employeeSkills;
    }
}
