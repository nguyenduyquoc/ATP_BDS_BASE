package com.anthinhphatjsc.ezisolutions.core.security;

import com.anthinhphatjsc.ezisolutions.core.auth.AuthenticationToken;
import com.anthinhphatjsc.ezisolutions.utils.StringUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class TokenAuthenticationFilter extends OncePerRequestFilter {


    private final AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = StringUtil.isNotEmpty(request.getHeader(AUTHORIZATION)) ? request.getHeader(AUTHORIZATION) : "";
        if (StringUtil.isEmpty(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = StringUtil.removeStart(token, "Bearer ").trim();

        try {
            Authentication authentication = authenticationManager.authenticate(new AuthenticationToken(token));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            logger.error(e);
        }
        filterChain.doFilter(request, response);
    }
}
