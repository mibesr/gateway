package com.demo.gateway.engine.handler.parse;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 解析原始报文处理器
 *
 * @author 隐墨星辰
 */
@Component
public class ParseOriginalResponseMessageHandler extends BaseHandler {

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {
        String originalResponseMessage = context.getOriginalResponseMessage();
        assertNotEmpty(originalResponseMessage, "originalResponseMessage can not be empty!");

        context.getResponseData().putAll(JSON.parseObject(context.getOriginalResponseMessage(), HashMap.class));
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
        return HandlerType.PARSE_ORIGINAL_RESPONSE_MESSAGE;
    }
}
