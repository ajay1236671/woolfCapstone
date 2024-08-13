package com.example.AuthSecurityService.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> {
            try {
                requests.anyRequest().permitAll().and().csrf().disable();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        });
        return httpSecurity.build();
    }
}
