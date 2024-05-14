package com.demo.gateway.engine.handler.assemble;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 组装外发请求报文处理器
 *
 * @author 隐墨星辰
 */
@Component
public class AssembleRequestMessageHandler extends BaseHandler {

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {
        String requestMessageTemplate = context.getInterfaceConf().getMessageConf().getRequestMessageTemplate();
        assertNotEmpty(requestMessageTemplate, "requestMessageTemplate can not be empty!");

        String requestMessage = GroovyUtil.make(requestMessageTemplate, context.getRuntimeContext());
        context.setAssembledRequestMessage(requestMessage);
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
        return HandlerType.ASSEMBLE_REQUEST_MESSAGE;
    }
}
