package com.demo.gateway.cache;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.model.InterfaceConf;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 接口配置缓存
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class InterfaceConfCache {
    private final Map<String, InterfaceConf> interfaceConfMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {

        // TODO 先使用一个文件做测试，真实数据根据自己项目需求放配置文件或数据库中
        String filePath = "conf/channel/demo/interface/pay.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();

        String fileContext = FileUtils.readFileToString(file);
        // TODO LOG
        System.out.println(fileContext);

        InterfaceConf interfaceConf = JSON.parseObject(fileContext, InterfaceConf.class);
        interfaceConfMap.put(interfaceConf.getName(), interfaceConf);
    }

    public InterfaceConf getInterfaceConfByName(String name) {
        return interfaceConfMap.get(name);
    }
}
