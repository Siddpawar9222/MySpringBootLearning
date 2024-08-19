package com.example.spring_caching_2.config;


import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfigWithCaching {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // Use String serializers for keys
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        // Use JSON serializer for values
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }

    // here Hash means nested object

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60)) // Set TTL for cache entries
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        redisConnectionFactory.getConnection().flushAll(); // clear redis cache when start the application (not recommended for distributed application)

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(cacheConfig)
                .build();
    }

}
/*
With help of above step up we can use redis cache in our application. We can use annotations like @Cacheable, @CachePut, @CacheEvict etc. directly with any manual code.

// **************************************** //

Serialization for RedisTemplate: Affects all direct Redis operations (e.g., manual puts and gets). If you're directly interacting with Redis using RedisTemplate, the serialization configured here will apply.

Serialization for CacheManager: Specifically affects how values are serialized and deserialized for cache entries. This is used when you are using Spring's caching abstraction (@Cacheable, @CachePut, etc.). It ensures that values stored in the cache adhere to the serialization rules defined here.

Automatic Handling: When you configure serializers, deserialization is automatically handled when you read from Redis, so you don't need to manually implement deserialization logic.

*/
