package com.demo.gateway.cache;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.model.InstConf;
import com.demo.gateway.model.InstConf;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class InstConfCache {
    private Map<String, InstConf> instConfMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {
        //TODO 先使用一个文件做测试
        String filePath = "conf/channel/demo/inst_conf/inst_conf.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        // TODO
        System.out.println(file.getAbsolutePath());

        InstConf instConf = JSON.parseObject(FileUtils.readFileToString(file), InstConf.class);
        instConfMap.put(instConf.getGroupName(), instConf);
    }

    public InstConf getInstConfByGroupName(String groupName) {
        return instConfMap.get(groupName);
    }
}
