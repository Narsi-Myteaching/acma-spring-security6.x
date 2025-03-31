package com.acma.properties.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public interface AppConstants {

    public BCryptPasswordEncoder pwdEncoder = new BCryptPasswordEncoder();

}
