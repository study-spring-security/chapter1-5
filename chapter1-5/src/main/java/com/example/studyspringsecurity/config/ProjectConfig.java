package com.example.studyspringsecurity.config;

import com.example.studyspringsecurity.authenticationHandler.CustomAuthenticationFailureHandler;
import com.example.studyspringsecurity.authenticationHandler.CustomAuthenticationSuccessHandler;
import com.example.studyspringsecurity.config.CustomAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@RequiredArgsConstructor
public class ProjectConfig {

    private final CustomAuthenticationProvider customAuthenticationProvider;

    private final CustomAuthenticationFailureHandler authenticationFailureHandler;
    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var auth = http.getSharedObject(AuthenticationManagerBuilder.class);
        auth.authenticationProvider(customAuthenticationProvider);
        return auth.build();
    }


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
                .httpBasic();

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        return http.build();
    }
}
