package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "employees", uniqueConstraints = @UniqueConstraint(columnNames = "employeeCode"))
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String employeeCode;

    @NotBlank
    private String fullName;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    private String designation;
    private String department;
    private Double yearsOfExperience;
    private String availabilityStatus; // AVAILABLE / ALLOCATED

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeSkill> employeeSkills;

    @OneToMany(mappedBy = "employee")
    private List<EmployeeCertification> employeeCertifications;

    // Constructors
    public Employee() {
        this.createdAt = LocalDateTime.now();
    }

    public Employee(String employeeCode, String fullName, String email, String designation,
                    String department, Double yearsOfExperience, String availabilityStatus) {
        this.employeeCode = employeeCode;
        this.fullName = fullName;
        this.email = email;
        this.designation = designation;
        this.department = department;
        this.yearsOfExperience = yearsOfExperience;
        this.availabilityStatus = availabilityStatus;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(String employeeCode) { this.employeeCode = employeeCode; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDesignation() { return designation; }
    public void setDesignation(String designation) { this.designation = designation; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Double getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(Double yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public List<EmployeeSkill> getEmployeeSkills() { return employeeSkills; }
    public void setEmployeeSkills(List<EmployeeSkill> employeeSkills) { this.employeeSkills = employeeSkills; }

    public List<EmployeeCertification> getEmployeeCertifications() { return employeeCertifications; }
    public void setEmployeeCertifications(List<EmployeeCertification> employeeCertifications) {
        this.employeeCertifications = employeeCertifications;
    }
}
