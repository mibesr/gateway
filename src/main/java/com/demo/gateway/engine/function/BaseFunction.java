package com.demo.gateway.engine.function;

/**
 * 内联函数基类
 *
 * @author 隐墨星辰
 */
public abstract class BaseFunction {

    private static final String FUNCTION = "Function";

    public String getName() {
        return this.getClass().getSimpleName().replace(FUNCTION, "");
    }
}
