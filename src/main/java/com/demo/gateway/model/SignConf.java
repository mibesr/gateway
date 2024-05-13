package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 签名配置
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
@AllArgsConstructor
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
}
