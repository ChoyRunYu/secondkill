package com.secondkill.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.BASE64Encoder;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

@SpringBootTest
class SecondkillAuthApplicationTests {

    @Test
    void contextLoads() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Key publicKey = keyPair.getPublic();

        Key privateKey = keyPair.getPrivate();

        System.out.println( (new BASE64Encoder()).encodeBuffer(privateKey.getEncoded()));

        System.out.println(  (new BASE64Encoder()).encodeBuffer(publicKey.getEncoded()));
    }

}
