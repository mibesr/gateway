package com.demo.gateway.model;

import lombok.*;

/**
 * 验签配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class VerifyConf {

    /**
     * 验签明文模板
     */
    private String verifyPlainTemplate;

    /**
     * 验签算法类型
     */
    private String verifyType;

    /**
     * 验签密钥索引号
     */
    private String verifyKeyIndex;

    /**
     * 签名算法
     */
    private String algorithm;
}
