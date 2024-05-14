package com.demo.gateway.cache;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.model.KeyConf;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 密钥配置缓存
 *
 * @author 隐墨星辰
 */
@Component
@Log4j2
public class KeyConfCache {
    private final Map<String, KeyConf> keyConfMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {

        //TODO 先使用一个文件做测试，真实数据根据自己项目需求放配置文件或数据库中

        KeyConf privateKey = loadKeyConf("conf/channel/demo/key/sign_private_key.json");
        KeyConf publicKey = loadKeyConf("conf/channel/demo/key/verify_public_key.json");

        keyConfMap.put(privateKey.getIndexName(), privateKey);
        keyConfMap.put(publicKey.getIndexName(), publicKey);
    }

    private KeyConf loadKeyConf(String filePath) throws IOException {
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();
        String fileContext = FileUtils.readFileToString(file);
        log.info("fileContext: {}", fileContext);
        return JSON.parseObject(fileContext, KeyConf.class);
    }

    public KeyConf getKeyConf(String indexName) {
        return keyConfMap.get(indexName);
    }

}
