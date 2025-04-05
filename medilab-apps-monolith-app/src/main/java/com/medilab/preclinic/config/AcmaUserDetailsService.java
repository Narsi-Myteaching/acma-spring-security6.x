package com.medilab.preclinic.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class AcmaUserDetailsService implements UserDetailsService {

    private final List<UserDetails> userDetailsList;

    public AcmaUserDetailsService(List<UserDetails> userDetailsList){
        this.userDetailsList = userDetailsList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDetailsList.stream()
                .filter(userDetails -> userDetails.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(()-> new UsernameNotFoundException("the supplied user doents exists in the user store"));
    }
}
