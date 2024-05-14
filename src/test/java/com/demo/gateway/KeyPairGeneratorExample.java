package com.demo.gateway;


import java.security.*;
import java.util.Base64;

/**
 * 密钥对生成测试
 *
 * @author 隐墨星辰
 */
public class KeyPairGeneratorExample {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());

        System.out.println("Private Key: " + privateKeyString);
        System.out.println("Public Key: " + publicKeyString);
    }
}