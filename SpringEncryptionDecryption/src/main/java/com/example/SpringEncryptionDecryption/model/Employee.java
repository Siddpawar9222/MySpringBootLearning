package com.example.SpringEncryptionDecryption.model;

import com.example.SpringEncryptionDecryption.util.AESUtil;
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
    private String password;
    private String mobileNo;
    private String gender;
    private  int age ;
    private  String nationality ;

    public void setPassword(String password) {
        try {
            this.password = AESUtil.encrypt(password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        try {
            return AESUtil.decrypt(this.password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
