package com.placementtracker.placement_tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers(
                                "/api/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Company APIs
                        .requestMatchers(HttpMethod.POST, "/api/company/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/api/company/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/api/company/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/company/**")
                        .authenticated()

                        // Application APIs
                        .requestMatchers(HttpMethod.POST, "/api/application/apply")
                        .hasRole("USER")

                        .requestMatchers( HttpMethod.GET, "/api/application/my")
                        .hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/api/application")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.PATCH, "/api/application/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()
                )
                .addFilterBefore ( jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder ();
    }
}