package com.thoughtworks.dps.restapirefimpl.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class User implements UserDetails {

    public static final List<User> USERS = Arrays.asList(new User("Alice", "qwerty", "ROLE_ADMIN"), new User("Bob", "foobar", "ROLE_USER"));
    private final String name;
    private final String token;
    private final String role;

    public User(@NotNull String name, String token, String role) {

        this.name = name;
        this.token = token;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return name.equals(user.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public String getToken() {
        return token;
    }
}
