package com.security.springsecurity2.service;

import com.security.springsecurity2.dto.UserDto;
import com.security.springsecurity2.exception.BadRequestException;
import com.security.springsecurity2.exception.ResourceUnavailableException;
import com.security.springsecurity2.jwt.JwtService;
import com.security.springsecurity2.model.ERole;
import com.security.springsecurity2.model.Role;
import com.security.springsecurity2.model.User;
import com.security.springsecurity2.repository.RoleRepository;
import com.security.springsecurity2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public ResponseEntity<?> registerUser(UserDto userDto) throws BadRequestException, ResourceUnavailableException {
        Boolean existsByUsername = userRepository.existsByUsername(userDto.getUsername());
        if (existsByUsername) {
            return ResponseEntity.badRequest().body("Username is already taken , Try another one");
        }

        List<String> roleList = userDto.getRoles();

        List<Role> role = new ArrayList<>();
        User user = User.builder()
                    .username(userDto.getUsername())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                     .build();

        if (roleList == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("USER role not available right now"));
            role.add(userRole);
        } else {
            for (String r : roleList) {
                if (r.equalsIgnoreCase("admin")) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new ResourceUnavailableException("ADMIN Role is not available "));
                    role.add(adminRole);
                } else if (r.equalsIgnoreCase("user")) {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new ResourceUnavailableException("USER Role is not available"));
                    role.add(userRole);
                } else {
                    throw new BadRequestException("Only USER or ADMIN role is available");
                }
            }
        }

        user.setRoles(role);
        userRepository.save(user) ;
        return ResponseEntity.ok("Registration Successful....Please Login") ;

    }

    public String authenticateAndGetToken(UserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        if (authentication.isAuthenticated()) {
            Integer userId = userRepository.findIdByUsername(userDto.getUsername());
            return jwtService.generateToken(userDto.getUsername(), userId);
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }
}
