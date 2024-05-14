package com.demo.gateway.model;

import lombok.*;

/**
 * 报文配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
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
