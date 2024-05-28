package com.anthinhphatjsc.ezisolutions.services.impl;

import com.anthinhphatjsc.ezisolutions.common.Filter;
import com.anthinhphatjsc.ezisolutions.core.auth.AuthenticationToken;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import com.anthinhphatjsc.ezisolutions.repositories.RoleRepositoryImpl;
import com.anthinhphatjsc.ezisolutions.repositories.UserRepositoryImpl;
import com.anthinhphatjsc.ezisolutions.services.IUserService;
import com.anthinhphatjsc.ezisolutions.utils.StringUtil;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Log4j2
@Component
@Validated
public class UserService implements IUserService {

    @Autowired
    UserRepositoryImpl repository;

    @Autowired
    RoleRepositoryImpl roleRepository;


    @Override
    public UserRepositoryImpl getRepository() {
        return repository;
    }

    @Override
    public UserEntity create(UserEntity entity) {
        entity.setPassword(StringUtil.hashPassword(entity.getPassword()));
        return IUserService.super.create(entity);
    }

    public UserEntity verify(UserEntity entity) {
        UserEntity user = this.find(listOf(Filter.query("username", entity.getUsername())));
        if (user != null && StringUtil.verifyPassword(entity.getPassword(), user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public UserEntity findByToken(String token) {
        return find(this.listOf(Filter.query("rememberToken", token)));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.find(listOf(Filter.query("username", username)));
    }

    @Transactional
    public AuthenticationToken authenticate(AuthenticationToken authenticationToken) {
        if (StringUtil.isNotEmpty(authenticationToken.getToken())) {
            UserEntity authenticatedUser = this.findByToken(authenticationToken.getToken());
            if (authenticatedUser != null) {
                authenticationToken = new AuthenticationToken(authenticationToken.getToken(), authenticatedUser);
                return authenticationToken;
            }
        }
        return authenticationToken;
    }
}
