package com.example.demo.model;

public class EmployeeSkill{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String proficiencyLevel;
    
}