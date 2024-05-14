package com.demo.gateway.engine.handler.convert;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 原始请求转换处理器
 *
 * @author 隐墨星辰
 */
@Component
public class ConvertOriginalRequestHandler extends BaseHandler {
    @Override
    public void run(HandlerEngineContext context) throws GatewayException {
        String requestContext = context.getRequestContext();
        Map<String, Object> originalRequestData = JSON.parseObject(requestContext, HashMap.class);

        context.getRequestData().putAll(originalRequestData);
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
