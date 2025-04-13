package com.medilab.preclinic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class AcmaPasswordPolicyValidator implements PasswordEncoder {

    @Value("${acma.password.policies.min-length}")
    private int minLengthRegEx;

    @Value("${acma.password.policies.upperCaseRegEx}")
    private String upperCaseRegEx;

    @Value("${acma.password.policies.numbersRegEx}")
    private String numbersRegEx;

    @Value("${acma.password.policies.specialCharsRegEx}")
    private String specialCharsRegEx;

    PasswordEncoder passwordEncoder;

    public AcmaPasswordPolicyValidator(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        if(!StringUtils.hasText(rawPassword) || rawPassword.length() < minLengthRegEx){
            throw new IllegalArgumentException("Password must be at least" +minLengthRegEx+ "characters long.");
        }

        if(!rawPassword.toString().matches(upperCaseRegEx)){
            throw new IllegalArgumentException("Password must contain at least one uppercase letter.");
        }

        if(!rawPassword.toString().matches(numbersRegEx)){
            throw new IllegalArgumentException("Password must contain at least one number.");
        }

        //if(!rawPassword.toString().matches(specialCharsRegEx)){
        if (!rawPassword.toString().matches(".*[!-\\/:-@[-`{-~]].*")) {
            throw new IllegalArgumentException("Password must contain at least special character.");
        }
        return true;
    }
}
