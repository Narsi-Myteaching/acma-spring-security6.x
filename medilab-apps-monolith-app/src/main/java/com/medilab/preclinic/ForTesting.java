package com.medilab.preclinic;

import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.security.crypto.keygen.StringKeyGenerator;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ForTesting {
    public static void main(String[] args) {
//        StringKeyGenerator keyGenerator = KeyGenerators.string();
//        String salt = keyGenerator.generateKey();
//        System.out.println("salt generated is "+salt);
//        BytesKeyGenerator keyGenerator = KeyGenerators.secureRandom();
//        byte [] key = keyGenerator.generateKey();
//        System.out.println("generated key is "+new String(key));
//        int keyLength = keyGenerator.getKeyLength();
//        System.out.println("Key Length is "+keyLength);

//        BytesKeyGenerator keyGenerator = KeyGenerators.shared(16);
//        byte [] key1 = keyGenerator.generateKey();
//        System.out.println("generated key is "+new String(key1));
//        byte [] key2 = keyGenerator.generateKey();
//        System.out.println("generated key is "+new String(key2));

//        String salt = KeyGenerators.string().generateKey();
//        String password = "secret";
//        String valueToEncrypt = "HELLO";
//
//        BytesEncryptor e = Encryptors.stronger(password, salt);
//        byte [] encrypted = e.encrypt(valueToEncrypt.getBytes());
//        System.out.println("encrypted val is "+new String(encrypted));
//
//        byte [] decrypted = e.decrypt(encrypted);
//        System.out.println("decrypted val is "+ new String(decrypted));

//        String valueToEncrypt = "HELLO";
//        TextEncryptor e = Encryptors.noOpText();
//        String encrypted = e.encrypt(valueToEncrypt);
//        System.out.println("encrypted is "+encrypted);

        String salt = KeyGenerators.string().generateKey();
        System.out.println("salt is "+salt);

        String password = "secret";
        String valueToEncrypt = "HELLO";

        TextEncryptor e = Encryptors.text(password, salt);

        String encrypted = e.encrypt(valueToEncrypt);
        System.out.println("encrypted is "+encrypted);

        String decrypted = e.decrypt(encrypted);
        System.out.println("decrypted is "+decrypted);

    }
}
