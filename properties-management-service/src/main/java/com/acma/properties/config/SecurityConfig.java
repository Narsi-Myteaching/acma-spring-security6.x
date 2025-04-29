/**
 * 
 */
package com.acma.properties.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((req)->
				req
						.anyRequest()
						.authenticated())
		     			.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
		http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		return  http.build();

	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
	 UrlBasedCorsConfigurationSource source = 
	 new UrlBasedCorsConfigurationSource();
	 CorsConfiguration config = new CorsConfiguration();
	 config.setAllowedOrigins(Arrays.asList("*"));
	 config.setAllowedMethods(Arrays.asList("*"));
	 config.setAllowedHeaders(Arrays.asList("*"));
	 config.setAllowCredentials(false);
	 config.applyPermitDefaultValues();
	 source.registerCorsConfiguration("/**", config);
	 return source;
	}
}
