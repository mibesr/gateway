package com.demo.gateway.engine.handler.encipher;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.model.SignConf;
import org.springframework.stereotype.Component;

/**
 * 签名处理器
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class SignHandler extends BaseHandler {

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {

        SignConf signConf = context.getInterfaceConf().getSignConf();

        // 没有配置签名，不需要签名
        if (null == signConf) {
            return;
        }

        //TODO 签名操作

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
        return HandlerType.SIGN;
    }
}
