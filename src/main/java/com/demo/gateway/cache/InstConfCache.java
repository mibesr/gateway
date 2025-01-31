package com.demo.gateway.cache;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.model.InstConf;
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
 * 机构配置缓存
 *
 * @author 隐墨星辰
 */
@Component
@Log4j2
public class InstConfCache {
    private final Map<String, InstConf> instConfMap = new HashMap<>();

    @PostConstruct
    public void init() throws IOException {

        //TODO 先使用一个文件做测试，真实数据根据自己项目需求放配置文件或数据库中
        String filePath = "conf/channel/demo/inst_conf/inst_conf.json";
        ClassPathResource resource = new ClassPathResource(filePath);
        File file = resource.getFile();

        String fileContext = FileUtils.readFileToString(file);
        log.info("fileContext: {}", fileContext);

        InstConf instConf = JSON.parseObject(fileContext, InstConf.class);
        instConfMap.put(instConf.getGroupName(), instConf);
    }

    public InstConf getInstConfByGroupName(String groupName) {
        return instConfMap.get(groupName);
    }
}
