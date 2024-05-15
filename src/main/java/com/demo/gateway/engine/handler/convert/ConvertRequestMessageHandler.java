package com.demo.gateway.engine.handler.convert;

import com.alibaba.fastjson.JSON;
import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 请求转换处理器
 *
 * @author 隐墨星辰
 */
@Component
public class ConvertRequestMessageHandler extends BaseHandler {
    @Override
    public void run(HandlerEngineContext context) throws GatewayException {

        String requestMessageTemplate = context.getInterfaceConf().getMessageConf().getRequestMessageTemplate();
        assertNotEmpty(requestMessageTemplate, "requestMessageTemplate can not be empty!");

        String requestMessage = GroovyUtil.make(requestMessageTemplate, context.getRuntimeContext());

        Map<String, Object> requestData = JSON.parseObject(requestMessage, HashMap.class);

        context.getRequestData().putAll(requestData);
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
        return HandlerType.CONVERT_REQUEST_MESSAGE;
    }
}
