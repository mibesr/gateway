package com.demo.gateway;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.engine.handler.chain.HandlerChainType;
import com.demo.gateway.model.*;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;

/**
 * 接口配置测试
 *
 * @author 隐墨星辰（公众号同名）
 */
@SpringBootTest
public class InterfaceConfTest {

    @Test
    public void writeFile() throws IOException {
        InterfaceConf interfaceConf = InterfaceConf.builder()
                .messageConf(buildMessageConf())
                .mockConf(buildMockConf())
                .sendConf(buildSendConf())
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
        System.out.println(file.getAbsolutePath());

        FileUtils.writeStringToFile(file, JSON.toJSONString(interfaceConf));

    }

    private VerifyConf buildVerifyConf() {
        return VerifyConf.builder().build();
    }

    private SignConf buildSignConf() {
        return SignConf.builder().build();
    }

    private SendConf buildSendConf() {
        return SendConf.builder()
                .host("${instConfData.host}")
                .url("${instConfData.payUrl}")
                .build();
    }

    private MockConf buildMockConf() {
        return MockConf.builder()
                .requestContext("")
                .requestMessage("")
                .responseMessage("")
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
                        "    \"payAmount\": \"${function.Money.of(responseData.amount, responseData.currency}\",\n" +
                        "    \"channelResponseCode\": \"${responseData.code}\",\n" +
                        "    \"channelResponseMessage\": \"${responseData.message}\"\n" +
                        "}")
                .build();
    }
}
