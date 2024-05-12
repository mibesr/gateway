package com.demo.gateway.api.request;


import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@ToString
public class GatewayRequest {
    @NotBlank(message = "requestId can not be blank|ILLEGAL_PARAM")
    @Length(min = 4, max = 32, message = "requestId must be more than 4 and less then 32|ILLEGAL_PARAM")
    private String requestId;

    private String source;

    private String interfaceName;

    private String requestContext;
}
