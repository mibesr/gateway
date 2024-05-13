package com.demo.gateway.groovy;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Groovy模板引擎处理
 *
 * @author 隐墨星辰（公众号同名）
 */
public class GroovyUtil {
    private static final TemplateEngine TEMPLATE_ENGINE = new SimpleTemplateEngine();
    private static final Map<String, Template> TEMPLATE_MAP = new ConcurrentHashMap<>();

    public static Template createTemplate(String string) {
        try {
            // TODO 考虑并发问题，加锁
            if (!TEMPLATE_MAP.containsKey(string)) {
                TEMPLATE_MAP.put(string, TEMPLATE_ENGINE.createTemplate(string));
            }

            return TEMPLATE_MAP.get(string);
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
