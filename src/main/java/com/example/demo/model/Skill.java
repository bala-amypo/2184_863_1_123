package com.example.demo.model;

import jarkarta.persistence.*;

@Entity
public class Skill{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String 
}