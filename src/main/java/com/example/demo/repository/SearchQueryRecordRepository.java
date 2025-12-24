package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SearchQueryRecord;

public interface SearchQueryRecordRepository extends JpaRepository<SearchQueryRecord, Long> {

    List<SearchQueryRecord> findBySearcherId(Long userId);
}
