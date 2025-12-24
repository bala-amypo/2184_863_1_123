package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;

    @NotBlank
    private String skillsRequested;

    private Integer resultsCount;

    private Timestamp searchedAt;

    // Constructors
    public SearchQueryRecord() {}

    public SearchQueryRecord(Long searcherId, String skillsRequested, Integer resultsCount) {
        if (skillsRequested == null || skillsRequested.trim().isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
    }

    @PrePersist
    protected void onCreate() {
        this.searchedAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getSearcherId() { return searcherId; }
    public void setSearcherId(Long searcherId) { this.searcherId = searcherId; }

    public String getSkillsRequested() { return skillsRequested; }
    public void setSkillsRequested(String skillsRequested) {
        if (skillsRequested == null || skillsRequested.trim().isEmpty()) {
            throw new IllegalArgumentException("must not be empty");
        }
        this.skillsRequested = skillsRequested;
    }

    public Integer getResultsCount() { return resultsCount; }
    public void setResultsCount(Integer resultsCount) { this.resultsCount = resultsCount; }

    public Timestamp getSearchedAt() { return searchedAt; }
    public void setSearchedAt(Timestamp searchedAt) { this.searchedAt = searchedAt; }
}
