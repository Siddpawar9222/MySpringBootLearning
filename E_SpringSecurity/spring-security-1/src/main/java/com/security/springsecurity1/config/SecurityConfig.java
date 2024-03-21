package com.security.springsecurity1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf->csrf.disable())
                    .cors(cors->cors.disable())
                   .authorizeHttpRequests((requests) ->
                           requests
                                   .requestMatchers("/api/public/*","/api/auth/*").permitAll()
                                   .requestMatchers("/api/security/admin").hasRole("ADMIN")
                                   .requestMatchers("/api/security/user").hasRole("USER")
                                   .anyRequest().authenticated()
                )
                    .formLogin(Customizer.withDefaults())
                    .httpBasic(Customizer.withDefaults())
                    .authenticationProvider(authenticationProvider());

        return http.build();
    }

}

/*
.formLogin(Customizer.withDefaults()):
.formLogin() is a configuration method in Spring Security used to enable and configure form-based authentication. Form-based authentication typically involves presenting users with a login form where they enter their credentials (e.g., username and password).


.httpBasic(Customizer.withDefaults()):
.httpBasic() is a configuration method that enables HTTP Basic Authentication. HTTP Basic Authentication involves sending credentials (username and password) in the HTTP request headers.

Customizer.withDefaults() is a convenience method that provides default settings for form-based authentication. It simplifies the configuration by applying sensible defaults, but you can further customize the behavior if needed.
 */