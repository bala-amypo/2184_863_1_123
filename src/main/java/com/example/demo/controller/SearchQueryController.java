package com.example.demo.controller;

import com.example.demo.dto.EmployeeSearchRequest;
import com.example.demo.model.Employee;
import com.example.demo.model.SearchQueryRecord;
import com.example.demo.service.SearchQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchQueryController {

    private final SearchQueryService searchService;

    public SearchQueryController(SearchQueryService searchService){
        this.searchService = searchService;
    }

    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> searchEmployees(@RequestBody EmployeeSearchRequest request){
        List<Employee> employees = searchService.searchEmployeesBySkills(request.getSkills(), request.getUserId());
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchQueryRecord> getQuery(@PathVariable Long id){
        return ResponseEntity.ok(searchService.getQueryById(id));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SearchQueryRecord>> getUserQueries(@PathVariable Long userId){
        return ResponseEntity.ok(searchService.getQueriesForUser(userId));
    }
}
