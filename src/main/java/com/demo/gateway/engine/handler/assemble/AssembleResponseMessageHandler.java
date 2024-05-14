package com.demo.gateway.engine.handler.assemble;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 组装返回报文处理器
 *
 * @author 隐墨星辰
 */
@Component
public class AssembleResponseMessageHandler extends BaseHandler {

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {
        String responseMessageTemplate = context.getInterfaceConf().getMessageConf().getResponseMessageTemplate();
        assertNotEmpty(responseMessageTemplate, "responseMessageTemplate can not be empty!");

        String responseMessage = GroovyUtil.make(responseMessageTemplate, context.getRuntimeContext());
        context.setAssembledResponseMessage(responseMessage);
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
        return HandlerType.ASSEMBLE_RESPONSE_MESSAGE;
    }
}
