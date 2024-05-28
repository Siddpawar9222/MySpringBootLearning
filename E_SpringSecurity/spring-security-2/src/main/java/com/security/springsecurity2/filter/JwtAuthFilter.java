package com.security.springsecurity2.filter;

import com.security.springsecurity2.config.UserDetailsServiceImpl;
import com.security.springsecurity2.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService ;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token =null ;
        String username =null ;
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

/*
What is OncePerRequestFilter ?
https://stackoverflow.com/questions/13152946/what-is-onceperrequestfilter

SecurityContextHolder.getContext().getAuthentication() used to  obtain the currently authenticated user's authentication details within your application.

UsernamePasswordAuthenticationToken:
If the token is valid, a new UsernamePasswordAuthenticationToken is created. This token represents the authenticated user.
userDetails is passed as the principal (the authenticated user) to the UsernamePasswordAuthenticationToken.
null is passed as the credentials (password), as the token is already authenticated by the JWT.
userDetails.getAuthorities() provides the user's authorities (roles and permissions) to the token.

Setting Authentication Details:
authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)) sets additional authentication details for the authToken.
WebAuthenticationDetailsSource().buildDetails(request) creates an instance of WebAuthenticationDetails containing details about the HTTP request, such as the client's IP address and session ID.

Updating Security Context:
SecurityContextHolder.getContext().setAuthentication(authToken) updates the security context to indicate that the user is authenticated.
The authToken representing the authenticated user is now stored in the security context, allowing the application to recognize the user as authenticated for subsequent requests during the same session.
 */