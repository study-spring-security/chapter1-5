package com.example.studyspringsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailService implements UserDetailsService {

    // UserDetailsService 는 메모리 내 사용자 목록을 괸라한다.
    private final List<UserDetails> users;

    public InMemoryUserDetailService(List<UserDetails> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(
                        u -> u.getUsername().equals(username)
                )
                .findFirst()
                .orElseThrow(
                        () -> new UsernameNotFoundException("User Not Found")
                );
    }
}
