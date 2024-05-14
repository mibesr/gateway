package com.demo.gateway.engine.handler.auth;

import com.demo.gateway.cache.KeyConfCache;
import com.demo.gateway.model.KeyConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * KeyStore助手
 *
 * @author 隐墨星辰
 */
@Component
public class KeyStoreHelper {

    @Autowired
    private KeyConfCache keyConfCache;

    private Map<String, PrivateKey> privateKeyMap = new HashMap<>();
    private Map<String, PublicKey> publicKeyMap = new HashMap<>();

    /**
     * 获取私钥
     *
     * @param privateKeyIndex
     * @return
     */
    public PrivateKey getPrivateKey(String privateKeyIndex) throws Exception {
        if (!privateKeyMap.containsKey(privateKeyIndex)) {
            KeyConf keyConf = keyConfCache.getKeyConf(privateKeyIndex);
            PrivateKey privateKey = getPrivateKeyFromString(keyConf.getValue());
            privateKeyMap.put(privateKeyIndex, privateKey);
        }

        return privateKeyMap.get(privateKeyIndex);
    }

    public PublicKey getPublicKey(String publicKeyIndex) throws Exception {
        if (!publicKeyMap.containsKey(publicKeyIndex)) {
            KeyConf keyConf = keyConfCache.getKeyConf(publicKeyIndex);
            PublicKey publicKey = getPublicKeyFromString(keyConf.getValue());
            publicKeyMap.put(publicKeyIndex, publicKey);
        }
        return publicKeyMap.get(publicKeyIndex);
    }

    private PrivateKey getPrivateKeyFromString(String privateKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(spec);
    }

    private PublicKey getPublicKeyFromString(String publicKeyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }
}
