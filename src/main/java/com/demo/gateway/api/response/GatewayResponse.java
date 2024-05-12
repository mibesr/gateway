package com.demo.gateway.api.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class GatewayResponse {
    private boolean success;
    private String errorMessage;
    private String responseContext;

}
