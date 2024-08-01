package com.example.spring_caching_1.controller;

import com.example.spring_caching_1.service.CacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/caches")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping
    public ResponseEntity<Collection<String>> getCacheNames() {
        return new ResponseEntity<>(cacheService.getCacheNames(), HttpStatus.OK);
    }

    @GetMapping("/{cacheName}")
    public ResponseEntity<Map<Object, Object>> getCacheContent(@PathVariable String cacheName) throws JsonProcessingException {
        return new ResponseEntity<>(cacheService.getCache(cacheName), HttpStatus.OK);
    }

}