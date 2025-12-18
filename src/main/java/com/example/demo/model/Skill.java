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
    public void setName(String name){
        this.name = name;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String id){
        this.category = category;
    }
    public String getDescription(){
        return descrition;
    }
    public void setDescription(Long id){
        this.description = description;
    }
    public Boolean getActive(){
        return id;
    }
    public void setActive(Long id){
        this.id = id;
    }
}