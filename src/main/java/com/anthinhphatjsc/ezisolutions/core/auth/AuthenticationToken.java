package com.anthinhphatjsc.ezisolutions.core.auth;

import com.anthinhphatjsc.ezisolutions.entities.RoleEntity;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AuthenticationToken implements Authentication {

    private boolean isAuthenticated;
    private UserEntity userDetails;
    @Getter
    private final String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public AuthenticationToken(String token, UserEntity userDetails) {
        this.token = token;
        this.userDetails = userDetails;
        this.isAuthenticated = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userDetails == null) {
            return new ArrayList<>();
        }
        Set<RoleEntity> roles = userDetails.getRoles();
        if (roles == null || roles.isEmpty()) {
            return new ArrayList<>();
        }
        return userDetails.getRoles().stream().map(e -> new SimpleGrantedAuthority(e.getCode())).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        throw new IllegalArgumentException("Not supported, use constructor");
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }
}
