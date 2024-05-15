package com.demo.gateway;

import com.demo.gateway.engine.handler.auth.SignVerifyHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 隐墨星辰
 */
@SpringBootTest
public class SignVerifyTest {

    @Autowired
    private SignVerifyHelper signVerifyHelper;

    @Test
    public void sign() throws Exception {
        String text = "{\"head\":{\"requestTime\":\"2024-09-12 12:00:02\",\"clientId\":\"9282832828322832\",\"function\":\"alipay.trade.pay\",\"version\":\"1.0\"},\"body\":{\"resultStatus\":\"S\",\"amount\":{\"currency\":\"CNY\",\"value\":8888},\"merchantId\":\"29232223\",\"resultCode\":\"0000\",\"paidTime\":\"2024-09-12 12:00:02\",\"resultMessage\":\"order is paid\",\"merchantOrderNo\":\"2024050112342230010111\"}}";
        String signature = signVerifyHelper.signRSA(text, "demo_channel_sign_private_key", "SHA256withRSA");

        System.out.println("signature: " + signature);

        boolean verify = signVerifyHelper.verifyRSA(text, signature, "demo_channel_verify_public_key", "SHA256withRSA");

        System.out.println("verify: " + verify);
    }


}
