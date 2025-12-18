package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
public class Employee{
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

    @PrePersist
    public void onCreate(){
        createdAt = new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    public void onUpdate(){
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getFullName(){
        return id;
    }
    public void setFullName(String fullName){
        this.fullName = fullName;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department = department;
    }
    public String getJobTitle(){
        return jobTitle;
    }
    public void setJobTitle(Boolean jobTitle){
        this.jobTitle = jobTitle;
    }
    public Boolean getActive(){
        return active;
    }
    public void setActive(Boolean active){
        this.active = active;
    }
    public Timestamp getCreatedAt(){
        return createdAt;
    }
    public void CreatedAt(Timestamp createdAt){
        this.createdAt = createdAt;
    }
    public Timestamp getUpdatedAt(){
        return createdAt;
    }
    public void setUpdatedAt(Timestamp updatedAt){
        this.updatedAt = updatedAt;
    }

}