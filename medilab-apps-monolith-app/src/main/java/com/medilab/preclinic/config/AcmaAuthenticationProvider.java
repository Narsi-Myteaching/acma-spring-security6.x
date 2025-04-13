package com.medilab.preclinic.config;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public class AcmaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("i am in Custom Authentication Provider");
        UserDetails userDetails =  userDetailsService.loadUserByUsername(authentication.getName());
        userDetails.getAuthorities().forEach(auth->System.out.println("AUTHORITIES ARE "+auth.getAuthority()));
        if(String.valueOf(authentication.getCredentials()).equals(userDetails.getPassword())){
            return  new UsernamePasswordAuthenticationToken(authentication.getName(),null, userDetails.getAuthorities());
        }else throw new BadCredentialsException("The Password is worng");

    }

    @Override
    public boolean supports(Class<?> authentication) {
        boolean isAuthnSupports = authentication.isInstance(UsernamePasswordAuthenticationToken.class);
        System.out.println("is Authn Supports "+isAuthnSupports);
        return true;
    }
}
