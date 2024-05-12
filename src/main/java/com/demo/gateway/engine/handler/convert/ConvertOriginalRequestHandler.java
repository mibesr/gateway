package com.demo.gateway.engine.handler.convert;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.EngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ConvertOriginalRequestHandler extends BaseHandler {
    @Override
    public void run(EngineContext context) throws GatewayException {
        String requestContext = context.getRequestContext();
        Map<String, Object> originalRequestData = JSON.parseObject(requestContext, HashMap.class);

        context.getOriginalRequestData().putAll(originalRequestData);
    }

    @Override
    public String getHandlerName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String getHandlerDesc() {
        return this.getClass().getSimpleName();
    }

    @Override
    public HandlerType getHandlerType() {
        return HandlerType.CONVERT_ORIGINAL_REQUEST_MESSAGE;
    }
}
