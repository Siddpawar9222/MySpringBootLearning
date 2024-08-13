//package com.example.spring_caching_2.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//@Configuration
//@Slf4j
//public class RedisConfigManual {
//
//    @Value("${spring.data.redis.port}")
//    private String port ;
//
//    @Value("${spring.data.redis.host}")
//    private String host ;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        LettuceConnectionFactory factory = new LettuceConnectionFactory();
//        factory.setPort(Integer.parseInt(port));
//        factory.setHostName(host);    // deprecated but still  using for clarification
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<String, String> redisTemplate() {
//        RedisTemplate<String, String> template = new RedisTemplate<>();
//
//        template.setConnectionFactory(redisConnectionFactory());
//        template.setKeySerializer(new StringRedisSerializer());
//        template.setValueSerializer(new StringRedisSerializer());
//
//        return template;
//    }
//
//}

/*
 With this setup, our application can interact with Redis by directly using the RedisTemplate bean. This allows you to perform basic Redis operations, such as inserting and retrieving string data, without using Spring’s caching abstractions (like @Cacheable or @CachePut).
Essentially, this configuration is focused on raw data operations rather than caching.
 */