package com.demo.gateway.api.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 网关请求
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
public class GatewayRequest {
    /**
     * 唯一ID
     */
    @NotBlank(message = "requestId can not be blank|ILLEGAL_PARAM")
    @Length(min = 4, max = 32, message = "requestId must be more than 4 and less then 32|ILLEGAL_PARAM")
    private String requestId;

    /**
     * 请求来源
     */
    @NotBlank(message = "source can not be blank|ILLEGAL_PARAM")
    @Length(min = 4, max = 16, message = "source must be more than 4 and less then 32|ILLEGAL_PARAM")
    private String source;

    /**
     * 渠道接口名称
     */
    @NotBlank(message = "interfaceName can not be blank|ILLEGAL_PARAM")
    @Length(min = 4, max = 64, message = "interfaceName must be more than 4 and less then 64|ILLEGAL_PARAM")
    private String interfaceName;

    /**
     * 发给渠道的请求参数，JSONString格式
     */
    private String requestContext;
}
