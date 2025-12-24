package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search-queries")
public class SearchQueryController {

    private final List<Map<String, Object>> queries = new ArrayList<>();

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addQuery(@RequestBody Map<String, Object> query) {
        queries.add(query);
        return ResponseEntity.ok(query);
    }

    @GetMapping("/")
    public ResponseEntity<List<Map<String, Object>>> getQueries() {
        return ResponseEntity.ok(queries);
    }
}
