package com.example.demo.model;

import jakarta.persistence.*;

public class SearchQueryRecord{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String categoryName;

    priv
}