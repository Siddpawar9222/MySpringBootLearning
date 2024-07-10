package com.security.springsecurity2.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "role_id")
    private Integer id ;

    @Column(name = "role_name")
    @Enumerated(EnumType.ORDINAL)
    private ERole name ;

    @ManyToMany(mappedBy = "roles")
    private List<User> users ;
}
