package com.demo.gateway.model;

import lombok.ToString;

/**
 * 报文类型
 *
 * @author 隐墨星辰
 */
@ToString
public enum MessageType {
    /**
     * JSON
     */
    JSON,

    /**
     * XML
     */
    XML,

    /**
     * KV
     */
    KV
}
