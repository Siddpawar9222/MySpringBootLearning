package com.example.spring_caching_1.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    public Map<Object, Object> getCache(String cacheName) throws JsonProcessingException {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Object allAvailableCache = cache.getNativeCache();
            ObjectMapper objectMapper = new ObjectMapper();
            String respData = objectMapper.writeValueAsString(allAvailableCache);
            TypeReference<Map<Object, Object>> typeRef = new TypeReference<>() {};
            Map<Object, Object> jsonMap = objectMapper.readValue(respData, typeRef);
            return jsonMap ;
        }
        return null;
    }
}
/*
TypeReference is a generic class provided by the Jackson library to capture type information during serialization and deserialization. It is used to represent a generic type, including type parameters and wildcards, at runtime.

When you create an instance of TypeReference, you can specify the type information using angle brackets (<>). For example, new TypeReference<Map<String, Object>>() {} creates a TypeReference instance that represents a Map with string keys and object values.
 */