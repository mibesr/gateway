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
import java.util.HashMap;
import java.util.Map;

/**
 * 机构配置测试
 *
 * @author 隐墨星辰（公众号同名）
 */
@SpringBootTest
public class InstConfTest {

    @Test
    public void writeFile() throws IOException {
        InstConf instConf = InstConf.builder()
                .groupName("demo_channel")
                .values(buildValues())
                .build();

        String filePath = "conf/channel/demo/inst_conf/inst_conf.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        System.out.println(file.getAbsolutePath());

        FileUtils.writeStringToFile(file, JSON.toJSONString(instConf));

    }

    private Map<String, String> buildValues() {
        Map<String, String> map = new HashMap<>();
        map.put("host", "http://www.demo.com");
        map.put("payUrl", "/pay.do");
        map.put("refundUrl", "/refund.do");
        map.put("signKeyIndex", "demo_private_key");

        return map;
    }


    @Test
    public void time() {
        System.out.println(System.currentTimeMillis());
    }
}
