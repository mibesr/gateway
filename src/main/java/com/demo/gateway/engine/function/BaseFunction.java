package com.demo.gateway.engine.function;

/**
 * 内联函数基类
 *
 * @author 隐墨星辰（公众号同名）
 */
public abstract class BaseFunction {

    /**
     * 不能重名，否则会被覆盖
     *
     * @return
     */
    public abstract String getName();
}
