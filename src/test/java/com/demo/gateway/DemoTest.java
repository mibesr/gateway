package com.demo.gateway;

import com.demo.gateway.api.GatewayService;
import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;
import com.demo.gateway.common.RuntimeHelper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Demo渠道UT
 *
 * @author 隐墨星辰
 */
@SpringBootTest
@Log4j2
public class DemoTest {

    @Autowired
    private GatewayService gatewayService;

    @Test
    public void run() {

        //TODO 不使用mock，发送外部渠道（本次是发给横批的渠道，需要先启动 com.demo.gateway.GatewayApplication.main）
//        run(false);

        // 使用mock，不发送真实渠道
        run(true);

    }

    public void run(boolean useMock) {

        // 使用mock，不发送真实渠道
        RuntimeHelper.init();
        RuntimeHelper.setUseMock(useMock);

        GatewayRequest request = buildRequest();

        GatewayResponse response = gatewayService.process(request);

        RuntimeHelper.clear();
        assertNotNull(response);
        assertTrue(response.isSuccess());

    }

    private GatewayRequest buildRequest() {
        return GatewayRequest.builder()
                .requestId("2024050112342230010111")
                .source("payment")
                .interfaceName("demo.pay")
                .requestContext("{\n" +
                        "    \"payAmount\": {\n" +
                        "        \"cent\": 8888,\n" +
                        "        \"currency\": \"RMB\"\n" +
                        "    },\n" +
                        "    \"orderNo\": \"2024050112342230010111\",\n" +
                        "    \"orderInfo\": \"test goods\",\n" +
                        "    \"requestTime\": 1715509833656\n" +
                        "}")
                .build();
    }

}
