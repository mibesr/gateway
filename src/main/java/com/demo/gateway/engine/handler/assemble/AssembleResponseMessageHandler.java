package com.demo.gateway.engine.handler.assemble;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.EngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import groovy.text.Template;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

@Component
public class AssembleResponseMessageHandler extends BaseHandler {

    @Override
    public void run(EngineContext context) throws GatewayException {
        String responseMessageTemplate = context.getInterfaceConf().getMessageConf().getResponseMessageTemplate();
        assertNotEmpty(responseMessageTemplate, "responseMessageTemplate can not be empty!");

        Template template = GroovyUtil.createTemplate(responseMessageTemplate);
        String responseMessage = template.make(context.getRuntimeContext()).toString();
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
