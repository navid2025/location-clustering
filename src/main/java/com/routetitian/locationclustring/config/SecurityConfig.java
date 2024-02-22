package com.routetitian.locationclustring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(RequestMatcherRegistry ->
                        RequestMatcherRegistry
                                .anyRequest()
                                .authenticated())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(SessionManagement ->
                        SessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    //Todo: use database configuration with authorities for userDetails in future
    //Todo: add roles to  userDetails
    //Todo: use globalMethod security. reason: it is more maintainable
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("k3enAnt16")
                //Todo: create a webservice for encrypt method for encoding password and dont use plaintext passwords
                .password(passwordEncoder().encode("]umpySilver63"))
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
