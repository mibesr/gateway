package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 发送配置
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class SendConf {

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
    private HeadConf headConf;
}
