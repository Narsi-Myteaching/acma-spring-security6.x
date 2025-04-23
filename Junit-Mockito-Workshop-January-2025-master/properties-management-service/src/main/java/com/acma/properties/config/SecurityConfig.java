/**
 * 
 */
package com.acma.properties.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 
 */
@Configuration
public class SecurityConfig {

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
