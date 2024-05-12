package com.demo.gateway.api;

import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;

/**
 * 服务入口
 *
 * @author 隐墨星辰（公众号同名）
 */
public interface GatewayService {

    /**
     * 处理
     *
     * @param request
     * @return
     */
    GatewayResponse process(GatewayRequest request);
}
