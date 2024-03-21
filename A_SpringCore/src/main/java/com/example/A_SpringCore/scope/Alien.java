package com.example.A_SpringCore.scope;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Component
@Lazy
//@Scope("prototype")
public class Alien {
	private Integer id;
	private String name;
	private String lang;
	public  Alien(){
		System.out.println("Alien bean is created.....");
	}
}
