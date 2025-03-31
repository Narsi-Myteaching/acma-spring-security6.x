package com.acma.properties.userdetails;

import com.acma.properties.utils.AppUserStore;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AcmaUserDetails implements UserDetailsService {


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return AppUserStore.inMemoryUserStore().loadUserByUsername(username);
    }
}
