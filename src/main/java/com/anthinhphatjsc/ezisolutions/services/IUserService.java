package com.anthinhphatjsc.ezisolutions.services;

import com.anthinhphatjsc.ezisolutions.core.BaseService;
import com.anthinhphatjsc.ezisolutions.core.auth.AuthenticationToken;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends BaseService<UserEntity>, UserDetailsService {
    public UserEntity verify(UserEntity entity);

    UserEntity findByToken(String token);

    public AuthenticationToken authenticate(AuthenticationToken jwtAuthenticationToken);
}
