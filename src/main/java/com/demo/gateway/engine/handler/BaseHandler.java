package com.demo.gateway.engine.handler;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.EngineContext;

public abstract class BaseHandler {
    public abstract void run(EngineContext context) throws GatewayException;

    public abstract String getHandlerName();

    public abstract String getHandlerDesc();

    public abstract HandlerType getHandlerType();
}
