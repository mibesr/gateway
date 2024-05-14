package com.demo.gateway.model;

import lombok.*;

/**
 * 签名配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SignConf {

    /**
     * 签名明文模板
     */
    private String signPlainTemplate;

    /**
     * 签名算法类型
     */
    private String signType;

    /**
     * 签名密钥索引号
     */
    private String signKeyIndex;

    /**
     * 签名算法
     */
    private String algorithm;
}
