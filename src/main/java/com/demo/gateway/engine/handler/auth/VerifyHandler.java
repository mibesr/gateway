package com.demo.gateway.engine.handler.auth;

import com.demo.gateway.common.GatewayException;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import com.demo.gateway.groovy.GroovyUtil;
import com.demo.gateway.model.SignType;
import com.demo.gateway.model.VerifyConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.hibernate.validator.internal.util.Contracts.assertNotEmpty;

/**
 * 验签处理器
 *
 * @author 隐墨星辰
 */
@Component
public class VerifyHandler extends BaseHandler {
    @Autowired
    private SignVerifyHelper signVerifyHelper;

    @Override
    public void run(HandlerEngineContext context) throws GatewayException {

        VerifyConf verifyConf = context.getInterfaceConf().getVerifyConf();

        // 没有配置验签，不需要验签
        if (null == verifyConf) {
            return;
        }

        String verifyPlainTemplate = verifyConf.getVerifyPlainTemplate();
        assertNotEmpty(verifyPlainTemplate, "verifyPlainTemplate can not be empty!");

        String verifyPlain = GroovyUtil.make(verifyPlainTemplate, context.getRuntimeContext());

        String signature = context.getResponseData().get("signature").toString();
        assertNotEmpty(signature, "signature can not be empty!");

        try {
            if (SignType.RSA.name().equals(verifyConf.getVerifyType())) {
                boolean isVerified = signVerifyHelper.verifyRSA(verifyPlain, signature, verifyConf.getVerifyKeyIndex(),
                        verifyConf.getAlgorithm());

                if (!isVerified) {
                    throw new GatewayException("Signature verification failed!");
                }
            } else {
                throw new GatewayException("Not support other verify method!");
            }
        } catch (GatewayException e) {
            throw e;
        } catch (Exception e) {
            throw new GatewayException("Verify error!", e);
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
        return HandlerType.VERIFY;
    }
}
