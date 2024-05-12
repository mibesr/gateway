package com.demo.gateway.api.response;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * 网关返回
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
public class GatewayResponse {
    /**
     * 网关处理成功
     * 注意：不代表业务成功，业务成功与否，需要单独判断渠道返回码
     */
    private boolean success;

    /**
     * 错误信息
     * 当success为false时才有值
     */
    private String errorMessage;

    /**
     * 返回的上下文，JSONString格式
     */
    private String responseContext;

}
