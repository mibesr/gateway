package com.demo.gateway.groovy;

import com.demo.gateway.common.GatewayException;
import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Groovy模板引擎处理
 *
 * @author 隐墨星辰
 */
public class GroovyUtil {
    private static final TemplateEngine TEMPLATE_ENGINE = new SimpleTemplateEngine();
    private static final Map<String, Template> TEMPLATE_MAP = new ConcurrentHashMap<>();

    public static String make(String template, Map<String, Object> binding) {
        try {
            Template t = createTemplate(template);
            return t.make(binding).toString();
        } catch (GatewayException e) {
            throw e;
        } catch (Exception e) {
            throw new GatewayException(e);
        }
    }

    private static Template createTemplate(String string) {
        try {
            // TODO 考虑并发问题，加锁
            if (!TEMPLATE_MAP.containsKey(string)) {
                TEMPLATE_MAP.put(string, TEMPLATE_ENGINE.createTemplate(string));
            }

            return TEMPLATE_MAP.get(string);
        } catch (ClassNotFoundException | IOException e) {
            throw new GatewayException(e);
        }
    }
}
