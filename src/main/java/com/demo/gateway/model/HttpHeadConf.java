package com.demo.gateway.model;

import lombok.*;

/**
 * Http请求头配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HttpHeadConf {
    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 客户端能够处理的内容类型
     */
    private String accept;

    /**
     * 客户端能够处理的语言
     */
    private String acceptLanguage;

    /**
     * 认证信息，用于对请求进行身份验证
     */
    private String authorization;

    /**
     * 指定请求和响应遵循的缓存机制
     */
    private String cacheControl;

    /**
     * 发出请求的用户代理的信息
     */
    private String userAgent;
}
