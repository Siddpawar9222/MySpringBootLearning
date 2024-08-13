package com.example.spring_caching_2;

import com.example.spring_caching_2.service.RedisTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableCaching
@Slf4j
public class SpringCaching2Application {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(SpringCaching2Application.class, args);
		RedisTestService redisTestService = applicationContext.getBean(RedisTestService.class);

		redisTestService.testRedis();

	}

}
