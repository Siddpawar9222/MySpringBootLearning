package com.example.springaop_1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private String name ;
    private String email;
    private String mobileNo;
    private String gender;
    private  int age ;
    private  String nationality ;
}
