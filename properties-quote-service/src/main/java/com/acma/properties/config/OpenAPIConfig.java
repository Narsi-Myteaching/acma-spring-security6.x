/**
 * 
 */
package com.acma.properties.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * 
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Acma Property Quote Service",
								description = "Acma Property Quote Service",
								version = "v1",
								license = @License(name = "Techhubvault",url = "https://www.techhubvault.com",identifier = "Copyright@2024"),
								contact = @Contact(email = "tickets-it@techhubvault.com")))
@SecurityScheme(name = "bearerAuth",scheme = "bearer",type = SecuritySchemeType.HTTP, bearerFormat = "JWT")
public class OpenAPIConfig {

}