package com.demo.gateway.engine.handler.auth;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.*;
import java.util.Base64;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * 签名验签帮助类
 *
 * @author 隐墨星辰
 */
@Component
public class SignVerifyHelper {

    @Autowired
    private KeyStoreHelper keyStoreHelper;

    /**
     * 使用私钥进行签名
     *
     * @param plainText
     * @param privateKeyIndex
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */

    public String signRSA(String plainText, String privateKeyIndex, String algorithm) throws Exception {
        PrivateKey privateKey = keyStoreHelper.getPrivateKey(privateKeyIndex);
        assertNotNull(privateKey, "privateKey can not be null!");

        Signature privateSignature = Signature.getInstance(algorithm);
        privateSignature.initSign(privateKey);
        privateSignature.update(plainText.getBytes());
        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    /**
     * 使用公钥进行验签
     *
     * @param plainText
     * @param signature
     * @param publicKeyIndex
     * @param algorithm
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     */
    public boolean verifyRSA(String plainText, String signature, String publicKeyIndex, String algorithm) throws Exception {
        PublicKey publicKey = keyStoreHelper.getPublicKey(publicKeyIndex);

        Signature publicSignature = Signature.getInstance(algorithm);
        publicSignature.initVerify(publicKey);
        publicSignature.update(plainText.getBytes());
        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }
}