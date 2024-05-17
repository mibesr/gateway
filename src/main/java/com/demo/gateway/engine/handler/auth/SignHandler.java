package com.demo.gateway.engine.handler.auth;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import com.demo.gateway.model.SignConf;
import com.demo.gateway.common.SignType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 签名处理器
 *
 * @author 隐墨星辰
 */
@Component
public class SignHandler extends BaseHandler {

    @Autowired
    private SignVerifyHelper signVerifyHelper;

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {

        SignConf signConf = context.getInterfaceConf().getSignConf();

        // 没有配置签名，不需要签名
        if (null == signConf) {
            return;
        }

        String signPlainTemplate = signConf.getSignPlainTemplate();
        assertNotEmpty(signPlainTemplate, "signPlainTemplate can not be empty!");

        String signPlain = GroovyUtil.make(signPlainTemplate, context.getRuntimeContext());

        try {
            if (SignType.RSA.name().equals(signConf.getSignType())) {
                String signature = signVerifyHelper.signRSA(signPlain, signConf.getSignKeyIndex(), signConf.getAlgorithm());

                context.setRequestSignature(signature);
            } else {
                throw new GatewayException("Not support other sign method!");
            }
        } catch (GatewayException e) {
            throw e;
        } catch (Exception e) {
            throw new GatewayException("Sign error!", e);
        }
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
