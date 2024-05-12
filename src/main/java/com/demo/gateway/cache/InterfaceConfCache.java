package com.demo.gateway.cache;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.model.InstConf;
import com.demo.gateway.model.InterfaceConf;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InterfaceConfCache {
    private Map<String, InterfaceConf> interfaceConfMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        //TODO 先使用一个文件做测试
        String filePath = "conf/channel/demo/interface/pay.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        // TODO
        System.out.println(file.getAbsolutePath());

        InterfaceConf interfaceConf = JSON.parseObject(FileUtils.readFileToString(file), InterfaceConf.class);
        interfaceConfMap.put(interfaceConf.getName(), interfaceConf);
    }

    public InterfaceConf getInterfaceConfByName(String name) {
        return interfaceConfMap.get(name);
    }
}
