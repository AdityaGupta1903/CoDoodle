package com.example.CoDoddleBE.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Security filter chain for form-based login (stateful)
    @Bean
    @Order(2) // Lower priority than @Order(1)
    public SecurityFilterChain formLoginSecurity(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/auth/**") // Only apply this chain to /auth/**
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .maxSessionsPreventsLogin(true)
                )
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    // Security filter chain for basic authentication (stateless)
    @Bean
    @Order(1)
    public SecurityFilterChain basicAuthSecurity(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/api/**") // Only apply this chain to /api/**
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/GetUser").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

