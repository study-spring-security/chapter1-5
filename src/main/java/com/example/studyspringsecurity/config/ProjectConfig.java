package com.example.studyspringsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class ProjectConfig {

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        var inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        var user = User
                .withUsername("jung")
                .password("123456")
                .authorities("read")
                .build();

        inMemoryUserDetailsManager.createUser(user);

        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder
                .userDetailsService(inMemoryUserDetailsManager)
                .passwordEncoder(passwordEncoder());


        return authenticationManagerBuilder.build();
    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .anyRequest()
                .permitAll();

        return http.build();
    }
}
