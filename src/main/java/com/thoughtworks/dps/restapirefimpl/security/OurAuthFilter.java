package com.thoughtworks.dps.restapirefimpl.security;

import com.thoughtworks.dps.restapirefimpl.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// this would do the stuff according to whatever you're using (i.e. OIDC, etc.) but for our purposes we're just going to
// hardcode 2 users
public class OurAuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = retrieveToken(authHeader);
        Optional<User> user = User.USERS.stream().filter(x -> x.getToken().equals(token)).findAny();

        if (user.isPresent()) {
            UserDetails ourUser = user.get();
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(ourUser, null, ourUser.getAuthorities()));
        }
        filterChain.doFilter(request, response);
    }

    private String retrieveToken(String authHeader) {
        return authHeader.substring(7);
    }

}
