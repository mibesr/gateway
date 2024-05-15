package com.demo.gateway.common;

import lombok.ToString;

/**
 * 签名算法
 *
 * @author 隐墨星辰
 */
@ToString
public enum SignType {

    /**
     * RSA
     */
    RSA,

    /**
     * HMAC
     */
    HMAC
}
