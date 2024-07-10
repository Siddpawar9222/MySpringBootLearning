package com.security.springsecurity1.dto;

import com.security.springsecurity1.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String username ;
    private String password ;
    private List<String> roles ;
}
