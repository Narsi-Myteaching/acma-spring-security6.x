package com.acma.properties.utils;

import com.acma.properties.App;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

public class AppUserStore {
    public static UserDetailsManager inMemoryUserStore(){
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User
                            .withUsername("TechhubVault")
                            .password(AppConstants.pwdEncoder.encode("Owner1234"))
                            .authorities("Owner").build());
        return  userDetailsManager;
    }
}
