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
                .host("${_C.host}")
                .url("${_C.payUrl}")
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
                        "    \"order_id\": \"${_O_REQ.orderNo}\",\n" +
                        "    \"amount\": \"${_O_REQ.payAmount.amount}\",\n" +
                        "    \"currency\": \"${_O_REQ.payAmount.currency}\",\n" +
                        "    \"mobile_no\": \"${_O_REQ.mobileNo}\",\n" +
                        "    \"order_info\": \"${_O_REQ.orderInfo}\",\n" +
                        "    \"date\": \"${_F.Datetime.formatDateString(_O_REQ.requestTime, 'yyyy-MM-dd HH:mm:ss')}\",\n" +
                        "    \"signature\": \"${_S.signatureMessage}\"\n" +
                        "}")
                .responseMessageTemplate("{\n" +
                        "    \"channelOrderNo\": \"${_O_RSP.order_id}\",\n" +
                        "    \"payAmount\": \"${_F.Money.of(_O_RSP.amount, _O_RSP.currency}\",\n" +
                        "    \"channelResponseCode\": \"${_O_RSP.code}\",\n" +
                        "    \"channelResponseMessage\": \"${_O_R.message}\"\n" +
                        "}")
                .build();
    }
}
