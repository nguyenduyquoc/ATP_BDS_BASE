package com.anthinhphatjsc.ezisolutions.utils;

import com.anthinhphatjsc.ezisolutions.core.auth.AuthenticationToken;
import com.anthinhphatjsc.ezisolutions.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthUtil {

    private final AuthenticationToken authToken;

    private static AuthUtil INSTANCE;

    public static AuthUtil getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AuthUtil();
        }
        return INSTANCE;
    }

    public AuthUtil() {
        authToken = (AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    }

    public UserEntity getCurrentUser() {
        return (UserEntity) authToken.getDetails();
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}
