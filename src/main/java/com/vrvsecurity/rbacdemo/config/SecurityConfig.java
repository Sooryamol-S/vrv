package com.vrvsecurity.rbacdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/api/users").hasRole("ADMIN")
                        .requestMatchers("/api/roles").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("admin".equals(username)) {
                return User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("adminpassword"))
                        .roles("ADMIN")
                        .build();
            }
            throw new UsernameNotFoundException("User not found");
        };
    }
}
