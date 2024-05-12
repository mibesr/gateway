package com.demo.gateway.engine.handler.assemble;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.EngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import groovy.text.Template;
import org.springframework.stereotype.Component;

@Component
public class AssembleRequestMessageHandler extends BaseHandler {

    @Override
    public void run(EngineContext context) throws GatewayException {
        Template template = GroovyUtil.createTemplate(
                context.getInterfaceConf().getMessageConf().getRequestMessageTemplate());
        String requestMessage = template.make(context.getRuntimeContext()).toString();
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
