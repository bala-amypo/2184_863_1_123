package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService searchQueryService;

    public SearchQueryController(SearchQueryService searchQueryService){
        this.searchQueryService = searchQueryService;
    }

    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestBody List<String> skills,
                                                          @RequestParam Long userId){
        List<Employee> result = searchQueryService.searchEmployeesBySkills(skills, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchQueryRecord> getQuery(@PathVariable Long id){
        return ResponseEntity.ok(searchQueryService.getQueryById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SearchQueryRecord>> getQueriesForUser(@PathVariable Long userId){
        return ResponseEntity.ok(searchQueryService.getQueriesForUser(userId));
    }
}
