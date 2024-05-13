package com.demo.gateway.engine;

import com.demo.gateway.engine.context.HandlerEngineContext;

/**
 * 处理器链引擎服务
 *
 * @author 隐墨星辰（公众号同名）
 */
public interface HandlerEngineService {

    /**
     * 执行
     *
     * @param context
     */
    void run(HandlerEngineContext context);
}
