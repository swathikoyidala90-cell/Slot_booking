package com.myslot.booking;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class securityconfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())   // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()    // Allow all requests
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())   // Needed for H2
            );

        // Disable httpBasic & form login like this (NO new lambdas)
        http.httpBasic(basic -> basic.disable());
        http.formLogin(form -> form.disable());

        return http.build();
    }
}
