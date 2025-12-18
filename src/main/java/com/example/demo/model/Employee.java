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

    private S
}