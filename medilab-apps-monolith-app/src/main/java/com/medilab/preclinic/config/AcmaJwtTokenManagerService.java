package com.medilab.preclinic.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@Component
public class AcmaJwtTokenManagerService {
    private static final String SECRETE_KEY = Base64.getEncoder().encodeToString("acma".getBytes());
    private static final long EXPIRATION_IN_MILLIS = 3600000; //1 hour

    private static final Date NOW = new Date();
    public static final long EXPIRY  = new Date(NOW.getTime()+EXPIRATION_IN_MILLIS).getTime();

    private static final String TOKEN_TYPE = "Bearer";
    private static final String HEADER_NAME = "Authorize";

    @Autowired
    private UserDetailsService userDetailsService;

    public String generateToken(String userName){
        UserDetails userDetails =  userDetailsService.loadUserByUsername(userName);
        List<String> rolesList = new ArrayList<>();
        userDetails.getAuthorities().forEach(auth-> rolesList.add(auth.getAuthority()));


        Map<String, List<String>> userRolesMap = new HashMap<>();
        userRolesMap.put("roles", rolesList);

        Claims claims = Jwts.claims()
                .subject(userName)
                .issuer("ACMA")
                .issuedAt(NOW)
                .expiration(new Date(EXPIRY))
                .add(userRolesMap)
                .add("scope","profile")
                .build();

        return Jwts.builder()
                .claims(claims)
                .signWith(SignatureAlgorithm.HS256, SECRETE_KEY)
                .audience()
                .add("http://localhost:3000")
                .and()
                .compact();
    }
}
