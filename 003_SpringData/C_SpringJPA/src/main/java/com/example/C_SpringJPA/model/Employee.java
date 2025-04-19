package com.example.springhealth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String name ;
    private String email;
    private String mobileNo;
    private String gender;
    private  int age ;
    private  String nationality ;
}
