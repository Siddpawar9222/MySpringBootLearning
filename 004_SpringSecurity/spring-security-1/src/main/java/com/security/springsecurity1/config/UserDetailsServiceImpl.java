package com.security.springsecurity1.config;

import com.security.springsecurity1.model.User;
import com.security.springsecurity1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository ;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not Found " + username));
        return UserDetailsImpl.build(user);
    }
}

/*
-DaoAuthenticationProvider invokes loadUserByUsername() of UserDetailsService , load user details, compare password with password of Authentication Object and valid the user.
-Spring Security may perform additional checks, such as verifying the user's account status (e.g., if the account is locked or expired) and checking for any additional authentication requirements, like two-factor authentication.
 */
