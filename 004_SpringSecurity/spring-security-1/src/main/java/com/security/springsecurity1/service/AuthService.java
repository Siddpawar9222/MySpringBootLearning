package com.security.springsecurity1.service;

import com.security.springsecurity1.dto.UserDto;
import com.security.springsecurity1.model.ERole;
import com.security.springsecurity1.model.Role;
import com.security.springsecurity1.model.User;
import com.security.springsecurity1.repository.RoleRepository;
import com.security.springsecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository ;
    public ResponseEntity<?> registerUser(UserDto userDto) {
        Boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername) {
            return ResponseEntity.badRequest().body("Username is already taken , Try another one");
        }

        List<String> roleList = userDto.getRoles();

        List<Role> role = new ArrayList<>();

        User user = User.builder()
                    .username(userDto.getUsername())
                    .password(userDto.getPassword())
                     .build();

        if (roleList == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("USER role not available right now"));
            role.add(userRole);
        } else {
            roleList.forEach(r -> {
                if (r.equalsIgnoreCase("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("ADMIN Role is not available"));
                    role.add(adminRole);
                } else if(r.equalsIgnoreCase("user")) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("USER Role is not available"));
                    role.add(userRole);
                }else{
                    throw new RuntimeException("Only USER or ADMIN role is available");
                }
            });
        }

        user.setRoles(role);
        userRepository.save(user) ;
        return ResponseEntity.ok("Registration Successful....Please Login") ;

    }
}
