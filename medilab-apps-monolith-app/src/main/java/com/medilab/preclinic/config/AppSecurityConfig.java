package com.medilab.preclinic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class AppSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.authorizeHttpRequests((requests) ->
                requests
                        .requestMatchers("/self-service/*").permitAll()
                        .requestMatchers("/appointments").authenticated()
                        .requestMatchers("/doctors").hasRole("DOCTOR").anyRequest().authenticated());
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // The builder will ensure the passwords are encoded before saving in memory
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails patient = users
                .username("user")
                .password("password")
                .roles("PATIENT")
                .build();

        UserDetails doctor = users
                .username("admin")
                .password("Password123$")
                .roles("PATIENT", "DOCTOR")
                .build();

        return new InMemoryUserDetailsManager(patient, doctor);
    }
}
