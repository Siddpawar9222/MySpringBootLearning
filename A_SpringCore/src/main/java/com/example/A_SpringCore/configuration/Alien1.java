package com.example.A_SpringCore.configuration;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ToString
@PropertySource("classpath:custom.properties")
public class Alien1 {
    @Value("${message}")
    private String message ;

    @Autowired
    private SelfKeyProps props ;

}
