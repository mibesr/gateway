package com.demo.gateway.web;

import com.demo.gateway.engine.handler.auth.SignVerifyHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 渠道模拟Controller
 *
 * @author 隐墨星辰
 */
@RestController
@Log4j2
public class DemoChannelMockController {

    @Autowired
    private SignVerifyHelper signVerifyHelper;

    @PostMapping("/pay.do")
    public String mockChannel(@RequestBody String request) throws Exception {

        log.info("request: {}", request);


        String signPlain = "{\"head\":{\"requestTime\":\"2024-09-12 12:00:02\",\"clientId\":\"9282832828322832\",\"function\":\"alipay.trade.pay\",\"version\":\"1.0\"},\"body\":{\"resultStatus\":\"S\",\"amount\":{\"currency\":\"CNY\",\"value\":8888},\"tradeNo\":\"20240501231932432423232\",\"merchantId\":\"29232223\",\"resultCode\":\"0000\",\"paidTime\":\"2024-09-12 12:00:02\",\"resultMessage\":\"order is paid\",\"merchantOrderNo\":\"2024050112342230010111\"}}";
        String signature = signVerifyHelper.signRSA(signPlain, "demo_channel_sign_private_key", "SHA256withRSA");

        String response =  "{\n  \"response\": " + signPlain + ",\n  \"signature\": \"" + signature + "\"\n}";


        log.info("response: {}", response);

        return response;
    }
}
