package com.example.spring_caching_2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Collection;

import org.springframework.data.redis.core.RedisTemplate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CacheService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Collection<String> getCacheNames() {
        return cacheManager.getCacheNames();
    }

    public Object getCacheContents(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            return getCacheKeysAndValues(cacheName);
        }
        return null;
    }

    private Object getCacheKeysAndValues(String cacheName) {
        Set<String> keys = redisTemplate.keys(cacheName + "*");
        log.info("Keys found for cache: " + cacheName + " - " + keys);

        if (keys == null || keys.isEmpty()) {
            log.info("No keys found for cache: " + cacheName);
            return null;
        }

        // Retrieve values for the keys
        return keys.stream()
                .collect(Collectors.toMap(
                        key -> key,
                        key -> redisTemplate.opsForValue().get(key)
                ));
    }
}
