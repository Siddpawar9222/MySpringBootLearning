package com.jdbc.C_SpringJDBC.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "employee")
@Entity
public class Employee {

    @Id
    private int id ;
    private String name ;
    private String email;
    private String mobileNo;
    private String gender;
    private  int age ;
    private  String nationality ;
}
