package com.example.A_SpringCore.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "key")
@Data
@Component
public class SelfKeyProps {

    private String nikename ;

    private  String email ;

    private String place ;

}
