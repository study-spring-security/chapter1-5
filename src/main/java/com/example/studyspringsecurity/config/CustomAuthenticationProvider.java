package com.example.studyspringsecurity.config;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 인증 논리를 추가할 위치
        String username = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        if ("jung".equals(username) && "123456".equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        }

        throw new AuthenticationCredentialsNotFoundException("Error in authentication!!");
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        // Authentication 형식의 구현을 추가할 위치

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }
}
