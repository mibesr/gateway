package com.demo.gateway.engine.handler;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;

/**
 * 处理器基类
 *
 * @author 隐墨星辰
 */
public abstract class BaseHandler {

    /**
     * 执行业务逻辑
     *
     * @param context
     * @throws GatewayException
     */
    public abstract void run(HandlerEngineContext context) throws GatewayException;

    public abstract String getHandlerName();

    public abstract String getHandlerDesc();

    public abstract HandlerType getHandlerType();
}
