package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;
    private String skillsRequested;
    private Integer resultsCount;
    private Timestamp searchedAt;

    // Constructors
    public SearchQueryRecord() {}

    public SearchQueryRecord(Long searcherId, String skillsRequested) {
        if (skillsRequested == null || skillsRequested.trim().isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
    }

    @PrePersist
    protected void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters & Setters
    public Long getId() { return id; }

    public Long getSearcherId() { return searcherId; }
    public void setSearcherId(Long searcherId) { this.searcherId = searcherId; }

    public String getSkillsRequested() { return skillsRequested; }
    public void setSkillsRequested(String skillsRequested) {
        this.skillsRequested = skillsRequested;
    }

    public Integer getResultsCount() { return resultsCount; }
    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public Timestamp getSearchedAt() { return searchedAt; }
}
