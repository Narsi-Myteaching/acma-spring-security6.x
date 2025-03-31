package com.acma.properties.authnproviders;

import com.acma.properties.userdetails.AcmaUserDetails;
import com.acma.properties.utils.AppConstants;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

public class AcmaAuthnProvider implements AuthenticationProvider {
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        System.out.println("User Name is "+userName);
        boolean isSuported = supports(authentication.getClass());
        if(isSuported){

        }
        AcmaUserDetails userDetailsService = new AcmaUserDetails();
        UserDetails  userDetails =  userDetailsService.loadUserByUsername(userName);
        if(!ObjectUtils.isEmpty(userDetails)){
            String userInputPwd = authentication.getCredentials().toString();
            boolean isPwdMatching = AppConstants.pwdEncoder.matches(userInputPwd,userDetails.getPassword());
            System.out.println("does passwords matched "+isPwdMatching);
            if(isPwdMatching){
                System.out.println("User Authentication Successful !!");
                List<GrantedAuthority> userGrantedRolesList = new ArrayList<GrantedAuthority>();
                userDetails.getAuthorities()
                        .forEach(role->userGrantedRolesList.add(new SimpleGrantedAuthority("ROLE_"+role.toString())));
                authentication = new UsernamePasswordAuthenticationToken(userName,null, userGrantedRolesList);

            } else {
                throw new BadCredentialsException("Bad Credentials supplied");
            }
        }
        return authentication;
    }

    public boolean supports(Class<?> authentication) {

        return true;
    }
}
