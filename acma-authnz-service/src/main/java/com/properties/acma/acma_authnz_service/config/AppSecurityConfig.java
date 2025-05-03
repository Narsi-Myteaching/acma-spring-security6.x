package com.properties.acma.acma_authnz_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((req)->
                 req
                         .requestMatchers("/acma/**","/oauth2/**")
                         .permitAll()
                         .anyRequest()
                         .authenticated());
        http
                .oauth2Client(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());


        http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        return  http.build();

    }


}
