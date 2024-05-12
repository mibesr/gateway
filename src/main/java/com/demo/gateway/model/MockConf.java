package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * MOCK配置
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class MockConf {

    /**
     *
     */
    private String requestContext;

    /**
     *
     */
    private String requestMessage;

    /**
     *
     */
    private String responseMessage;

    /**
     *
     */
    private String responseContext;
}
