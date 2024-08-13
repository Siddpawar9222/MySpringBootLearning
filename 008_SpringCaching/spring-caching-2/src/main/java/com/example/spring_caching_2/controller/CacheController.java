package com.example.spring_caching_2.controller;

import com.example.spring_caching_2.service.CacheService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@RestController
@RequestMapping("/api/caches")
@Slf4j
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping
    public ResponseEntity<Collection<String>> getCacheNames() {
        return new ResponseEntity<>(cacheService.getCacheNames(), HttpStatus.OK);
    }

    @GetMapping("/{cacheName}")
    public ResponseEntity<Object> getCacheContent(@PathVariable String cacheName)  {
        log.info("getCacheContent has started ::: " + new Date());
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(cacheService.getCacheContents(cacheName), HttpStatus.OK);
        log.info("getCacheContent has Ended ::: " + new Date());
        return objectResponseEntity ;
    }

}