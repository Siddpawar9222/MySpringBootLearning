package com.example.A_SpringCore.configuration;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Alien {
	private Integer id;
	private String name;
	private String lang;

	@Value("${key.nikename}")
	private String nikename ;

	@Value("${key.email}")
	private  String email ;


}
