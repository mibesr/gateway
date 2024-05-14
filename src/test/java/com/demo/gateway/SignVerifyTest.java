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
        String text = "{\n" +
                "    \"order_id\": \"8888888888899999999\",\n" +
                "    \"amount\": \"888\",\n" +
                "    \"currency\": \"RMB\",\n" +
                "    \"code\": \"000000\",\n" +
                "    \"message\": \"success\",\n" +
                "    \"date\": \"2024-05-12 18:00:24\"\n" +
                "}";
        String signature = signVerifyHelper.signRSA(text, "demo_channel_sign_private_key", "SHA256withRSA");

        System.out.println("signature: " + signature);

        boolean verify = signVerifyHelper.verifyRSA(text, signature, "demo_channel_verify_public_key", "SHA256withRSA");

        System.out.println("verify: " + verify);
    }


}
