package com.demo.gateway.engine.handler.send;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.stereotype.Component;

/**
 * 发送处理器
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class SendHandler extends BaseHandler {

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {
        if (context.isUseMock()) {
            // 使用mock
            context.setOriginalResponseMessage(context.getInterfaceConf().getMockConf().getResponseContext());
        }

        // TODO 外发处理

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
        return HandlerType.SEND;
    }
}
