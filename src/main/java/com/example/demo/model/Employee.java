package com.example.demo.model;

import jakarta.presistance.*;
import java.sql.Timestamp;

@Entity
public class Employee{
    @Id
    @GeneratedValue(stratergy = GenerationType.IDENTITY)
    private Long id;
    
    private String fullName;

    @Column(unique = true)
    private String email;

    private String department;
    private String jobTitle;

    private Boolean active = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @PrePresist
    public void onCreate(){
        createdAt = new Timestamp(System.currentTimeMillis());
    }
    @PreUpdate
    public void onUpdate(){
        updatedAt = new 
    }
}