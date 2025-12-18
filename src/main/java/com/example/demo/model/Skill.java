package com.example.demo.model;

import jarkarta.persistence.*;

@Entity
public class Skill{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String category;
    private String description;
    private Boolean active = true;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Long getName(){
        return id;
    }
    public void setName(Long name){
        this.name = name;
    }
    
}