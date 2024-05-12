package com.demo.gateway.groovy;

import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import groovy.text.TemplateEngine;

import java.io.IOException;

public class GroovyUtil {
    private static TemplateEngine templateEngine = new SimpleTemplateEngine();

    public static Template createTemplate(String string) {
        // TODO 需要缓存加速
        try {
            return new SimpleTemplateEngine().createTemplate(string);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
