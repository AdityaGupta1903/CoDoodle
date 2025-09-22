package com.example.CoDoddleBE.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
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

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails user1 = User.withUsername("my_username_1").password("{noop}my-password").roles("admin").build(); // {noop} means no encoding or no hashing
//        UserDetails user2 = User.withUsername("my_username_2").password(new BCryptPasswordEncoder().encode("my_password_1")).roles("USER").build();
//        return new InMemoryUserDetailsManager(user1,user2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {  // This Step is to remove the filter from the /auth/register method
        http.authorizeHttpRequests(auth->auth.requestMatchers("/auth/register").permitAll().anyRequest().authenticated()).csrf(csfr->csfr.disable()).sessionManagement(session->session.maximumSessions(1).maxSessionsPreventsLogin(true)).formLogin(Customizer.withDefaults());
        return http.build();
    }
}
