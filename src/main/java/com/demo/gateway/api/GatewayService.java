package com.demo.gateway.api;

import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;

/**
 * 服务入口
 */
public interface GatewayService {
    GatewayResponse process(GatewayRequest request);
}
