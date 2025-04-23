package com.medilab.preclinic.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class AppSecurityConfig {

    //@Autowired
    //private AcmaAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private AcmaVistorsFilter acmaVistorsFilter;
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
////        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
////        http.authorizeHttpRequests((requests) ->
////                requests
////                        .requestMatchers("/self-service/*").permitAll()
////                        .requestMatchers("/appointments").authenticated()
////                        .requestMatchers("/doctors").hasRole("DOCTOR").anyRequest().authenticated());
//
//        //http.authorizeHttpRequests((req)->req.anyRequest().permitAll());
//        //http.authorizeHttpRequests((req)->req.anyRequest().hasAuthority("VIEW"));
//        //http.authorizeHttpRequests((req)->req.anyRequest().hasAnyAuthority("VIEW","EDIT"));
//        //http.authorizeHttpRequests((req)->req.anyRequest().access(new WebExpressionAuthorizationManager("hasAnyAuthority(\"VIEW\",\"EDIT\")")));
////        http.authorizeHttpRequests((req)->req.anyRequest()
////                .access(new WebExpressionAuthorizationManager("hasAuthority(\"VIEW\") and !hasAuthority(\"EDIT\")")));
//
//        http.authorizeHttpRequests((req)->req.anyRequest().hasRole("DOCTOR"));
//        http.csrf(c->c.disable());
//
//
//
//        http.authenticationProvider(customAuthnProvider()).formLogin(withDefaults());
//        http.authenticationProvider(customAuthnProvider()).httpBasic(withDefaults());
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((req)->req
//                .requestMatchers("/self-service/**").permitAll()
//                .requestMatchers("/doctors").hasRole("DOCTORS")
//                .requestMatchers("/patients","/appointments").hasRole("PATIENTS")
//                .requestMatchers("/departments").hasAnyRole("DOCTORS","PATIENTS")
//                .anyRequest().authenticated());
//
//
//        http.authenticationProvider(customAuthnProvider()).formLogin(withDefaults());
//        http.authenticationProvider(customAuthnProvider()).httpBasic(withDefaults());
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((req)->req
//                .requestMatchers("/self-service/**","/assets/**","/api/**").permitAll()
//                .requestMatchers(HttpMethod.POST,"/api/**").permitAll()
//                .anyRequest().authenticated());
//
//        http.exceptionHandling((exception) -> exception.accessDeniedHandler(accessDeniedHandler));
//        http.addFilterBefore(acmaVistorsFilter, UsernamePasswordAuthenticationFilter.class);
//        //http.authenticationProvider(customAuthnProvider()).formLogin((form)->form.disable());
//        http.sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.authenticationProvider(customAuthnProvider()).httpBasic((basic)->basic.disable());
//        return http.build();
//    }

//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((req)->req
//                .requestMatchers(HttpMethod.POST,"/api/authenticate").permitAll()
//                .anyRequest().authenticated())
//                .csrf((csrfRef)->csrfRef.disable())
//                .httpBasic(withDefaults())
//                .sessionManagement((sessionRef)->sessionRef.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return  http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http.csrf((csrf) -> csrf.disable())
                .sessionManagement((sessionManagement) -> sessionManagement.
                        sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authorizeHttpRequests) ->
                        authorizeHttpRequests.requestMatchers(HttpMethod.POST,
                                "/api/authenticate").permitAll().anyRequest().authenticated());
        return http.build();
    }


//    @Bean
//    public UserDetailsService users() {
//        // The builder will ensure the passwords are encoded before saving in memory
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//        UserDetails patient = users
//                .username("user")
//                .password("password")
//                .roles("PATIENT")
//                .build();
//
//        UserDetails doctor = users
//                .username("admin")
//                .password("{bcrypt}Password123$")
//                .roles("PATIENT", "DOCTOR")
//                .build();
//
//        return new InMemoryUserDetailsManager(patient, doctor);
//    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource){
        return  new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public AuthenticationProvider customAuthnProvider(){
        return  new AcmaAuthenticationProvider();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new AcmaPasswordPolicyValidator(NoOpPasswordEncoder.getInstance());
    }
}
