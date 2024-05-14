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
