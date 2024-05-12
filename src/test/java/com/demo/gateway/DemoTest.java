package com.demo.gateway;

import com.demo.gateway.api.GatewayService;
import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DemoTest {

    @Autowired
    private GatewayService gatewayService;

    @Test
    public void run() {
        GatewayRequest request = buildRequest();

        GatewayResponse response = gatewayService.process(request);

        System.out.println(response);
        assertNotNull(response);
        assertTrue(response.isSuccess());
    }

    private GatewayRequest buildRequest() {
        return GatewayRequest.builder()
                .requestId("12345678901234567890123456789012")
                .source("test")
                .interfaceName("demo.pay")
                .requestContext("{\n" +
                        "    \"payAmount\": {\n" +
                        "        \"amount\": 993,\n" +
                        "        \"currency\": \"USD\"\n" +
                        "    },\n" +
                        "    \"orderNo\": \"1111111111222222222333333333344\",\n" +
                        "    \"userNo\": \"13888888888\",\n" +
                        "    \"orderInfo\": \"test goods\",\n" +
                        "    \"requestTime\": 1715509833656\n" +
                        "}")
                .build();
    }

}
