package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class MockConf {
    private String requestContext;
    private String requestMessage;
    private String responseMessage;
    private String responseContext;
}
