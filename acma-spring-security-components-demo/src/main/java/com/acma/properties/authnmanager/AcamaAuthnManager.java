package com.acma.properties.authnmanager;

import com.acma.properties.authnproviders.AcmaAuthnProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class AcamaAuthnManager implements AuthenticationManager {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AcmaAuthnProvider authnProvider = new AcmaAuthnProvider();
        return authnProvider.authenticate(authentication);
    }
}
