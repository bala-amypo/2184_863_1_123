package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_certifications")
public class EmployeeCertification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "certification_id", nullable = false)
    private Certification certification;

    public EmployeeCertification() {}

    public EmployeeCertification(Employee employee, Certification certification) {
        this.employee = employee;
        this.certification = certification;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Certification getCertification() { return certification; }
    public void setCertification(Certification certification) { this.certification = certification; }
}
