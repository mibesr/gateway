package com.demo.gateway.model;

import lombok.*;

/**
 * 发送配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HttpSendConf {

    /**
     * host
     */
    private String host;

    /**
     * url
     */
    private String url;

    /**
     * 头配置
     */
    private HttpHeadConf httpHeadConf;
}
