package com.example.demo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "search_query_records")
public class SearchQueryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long searcherId;

    @Column
    private String skillsRequested;

    private Integer resultsCount;

    private LocalDateTime searchedAt;
    @PrePersist
    public void onCreate() {
        this.searchedAt = LocalDateTime.now();
        if (resultsCount == null) resultsCount = 0;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public Long getSearcherId() {
        return searcherId;
    }

    public void setSearcherId(Long searcherId) {
        this.searcherId = searcherId;
    }

    public String getSkillsRequested() {
        return skillsRequested;
    }

    public void setSkillsRequested(String skillsRequested) {
        this.skillsRequested = skillsRequested;
    }

    public Integer getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(Integer resultsCount) {
        this.resultsCount = resultsCount;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }
    
    public SearchQueryRecord() {
    }

    public SearchQueryRecord(Long id,Long searcherId, String skillsRequested, Integer resultsCount) {
        this.id = id;
        this.searcherId = searcherId;
        this.skillsRequested = skillsRequested;
        this.resultsCount = resultsCount;
    }
}
