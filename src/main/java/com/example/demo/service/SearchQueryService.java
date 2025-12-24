package com.example.demo.service;

import java.util.List;
import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;

public interface SearchQueryService {

    List<Employee> searchEmployeesBySkills(List<String> skills, Long userId);

    void saveQuery(SearchQueryRecord record);

    SearchQueryRecord getQueryById(Long id);

    List<SearchQueryRecord> getQueriesForUser(Long userId);
}
