package com.demo.gateway;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.demo.gateway.common.MessageType;
import com.demo.gateway.engine.chain.HandlerChainType;
import com.demo.gateway.model.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * 接口配置测试
 *
 * @author 隐墨星辰
 */
@SpringBootTest
@Log4j2
public class InterfaceConfTest {

    @Test
    public void writeFile() throws IOException {
        InterfaceConf interfaceConf = InterfaceConf.builder()
                .messageConf(buildMessageConf())
                .mockConf(buildMockConf())
                .httpSendConf(buildSendConf())
                .instConfGroupName("demo_channel")
                .name("demo.pay")
                .signConf(buildSignConf())
                .verifyConf(buildVerifyConf())
                .messageType(MessageType.JSON)
                .handlerChainType(HandlerChainType.INNER_TO_OUTER)
                .build();

        String filePath = "conf/channel/demo/interface/pay.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        log.info("file: {}", file.getAbsolutePath());

        String json = JSON.toJSONString(interfaceConf, SerializerFeature.WriteEnumUsingToString);
        log.info("json: {}", json);

        FileUtils.writeStringToFile(file, json);

    }

    private VerifyConf buildVerifyConf() {
        return VerifyConf.builder()
                .verifyPlainTemplate("{\n    \"order_id\": \"${responseData.order_id}\",\n    \"amount\": \"${responseData.amount}\",\n    \"currency\": \"${responseData.currency}\",\n    \"code\": \"${responseData.code}\",\n    \"message\": \"${responseData.message}\",\n    \"date\": \"${responseData.date}\"\n}")
                .algorithm("SHA256withRSA")
                .verifyKeyIndex("demo_channel_verify_public_key")
                .verifyType("RSA")
                .build();
    }

    private SignConf buildSignConf() {
        return SignConf.builder()
                .signType("RSA")
                .algorithm("SHA256withRSA")
                .signKeyIndex("demo_channel_sign_private_key")
                .signPlainTemplate("{\n    \"order_id\": \"${requestData.orderNo}\",\n    \"amount\": \"${requestData.payAmount.amount}\",\n    \"currency\": \"${requestData.payAmount.currency}\"\n}")
                .build();
    }

    private HttpSendConf buildSendConf() {
        return HttpSendConf.builder()
                .host("${instConfData.host}")
                .url("${instConfData.payUrl}")
                .build();
    }

    private MockConf buildMockConf() {
        return MockConf.builder()
                .requestContext("")
                .assembledRequestMessage("")
                .originalResponseMessage("{\n" +
                        "    \"order_id\": \"8888888888899999999\",\n" +
                        "    \"amount\": 888,\n" +
                        "    \"currency\": \"RMB\",\n" +
                        "    \"code\": \"000000\",\n" +
                        "    \"message\": \"success\",\n" +
                        "    \"date\": \"2024-05-12 18:00:24\",\n" +
                        "    \"signature\": \"XEMtCFsYxNYCTIsXY0207BW1OqJlBiezEgqk+gnpiJo/uD7llsXQ9qjBR35WLGOyNezw5wmowCDnQpBmMEMtyTfbJfTa7b8TisjZquo6p0JTZSxMBWF/Iv0E6BG+zHHh6CwN7TRB6zWG/HP7cp1uIQv0NQ2j/wDCs4+OieKIRqrsQYSAY6qG21gBriUKQmVlWf6FGKpX8g7eFiSabnDNSH9tTDuSbn9nmkbldXqVvbuqQVRb8TxqQCy65Mbfsd4te2oFl4qPpqGXkdk9TezxWF9+GUqwbbFkWJe4T2961slr6dfNeeD8sM5qhs7RFV3IRUc6AYyqqFJJzO+cqirNug==\"\n" +
                        "}")
                .responseContext("")
                .build();
    }

    private MessageConf buildMessageConf() {
        return MessageConf.builder()
                .requestMessageTemplate("{\n" +
                        "    \"order_id\": \"${requestData.orderNo}\",\n" +
                        "    \"amount\": \"${requestData.payAmount.amount}\",\n" +
                        "    \"currency\": \"${requestData.payAmount.currency}\",\n" +
                        "    \"mobile_no\": \"${requestData.mobileNo}\",\n" +
                        "    \"order_info\": \"${requestData.orderInfo}\",\n" +
                        "    \"date\": \"${function.Datetime.formatDateString(requestData.requestTime, 'yyyy-MM-dd HH:mm:ss')}\",\n" +
                        "    \"signature\": \"${requestData.signatureMessage}\"\n" +
                        "}")
                .responseMessageTemplate("{\n" +
                        "    \"channelOrderNo\": \"${responseData.order_id}\",\n" +
                        "    \"payAmount\": \"${function.Money.of(responseData.amount, responseData.currency)}\",\n" +
                        "    \"channelResponseCode\": \"${responseData.code}\",\n" +
                        "    \"channelResponseMessage\": \"${responseData.message}\"\n" +
                        "}")
                .build();
    }
}
