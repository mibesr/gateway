package com.demo.gateway.model;

import lombok.*;

/**
 * MOCK配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MockConf {

    /**
     * 请求上下文，
     * 在内部发外部场景下，就是内部应用发过来的原始请求
     */
    private String requestContext;

    /**
     * 组装后的请求报文
     * 在内部发外部场景下，用于组装好后发给外部渠道的报文
     */
    private String assembledRequestMessage;

    /**
     * 原始返回报文
     * 在内部发外部场景下，就是外部渠道返回的原始报文
     */
    private String originalResponseMessage;

    /**
     * 返回上下文
     * 在内部发外部场景下，就是转换后返回给内部应用的报文
     */
    private String responseContext;
}
