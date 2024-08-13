package com.example.spring_caching_2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RedisTestService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void testRedis() {

        //set value
        redisTemplate.opsForValue().set("email", "email@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");
        log.info("redis email : " + email);

        //get value
        String name = redisTemplate.opsForValue().get("name");
        log.info("redis name : " + name);
    }

}
