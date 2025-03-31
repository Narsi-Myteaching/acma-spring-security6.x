package com.acma.properties;

import com.acma.properties.authnmanager.AcamaAuthnManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        Authentication userAuthnDetails = new UsernamePasswordAuthenticationToken("TechhubVault", "Owner1234");
        AcamaAuthnManager authnManager = new AcamaAuthnManager();
        Authentication principalObj = authnManager.authenticate(userAuthnDetails);
        System.out.println("is User Authenticated "+principalObj.isAuthenticated());
        if(principalObj.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(principalObj);
        }
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach(grantedRole -> System.out.println(grantedRole));
    }
}
