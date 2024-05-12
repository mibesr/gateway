package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 报文配置
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class MessageConf {

    /**
     * 外发请求报文模板
     */
    private String requestMessageTemplate;

    /**
     * 返回报文模板
     */
    private String responseMessageTemplate;
}
