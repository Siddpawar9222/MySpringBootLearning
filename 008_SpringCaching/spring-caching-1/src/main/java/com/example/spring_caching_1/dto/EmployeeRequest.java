package com.example.spring_caching_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeRequest {
    private String name ;
    private String email;

    private String mobileNo;

    private String gender;

    private  int age ;

    private  String nationality ;
}
