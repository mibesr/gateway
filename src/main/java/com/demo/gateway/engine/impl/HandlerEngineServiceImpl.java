package com.demo.gateway.engine.impl;

import com.demo.gateway.cache.FunctionCache;
import com.demo.gateway.cache.InstConfCache;
import com.demo.gateway.cache.InterfaceConfCache;
import com.demo.gateway.common.RuntimeHelper;
import com.demo.gateway.engine.HandlerEngineService;
import com.demo.gateway.engine.context.EngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.chain.HandlerChainFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

@Component
public class HandlerEngineServiceImpl implements HandlerEngineService {

    @Autowired
    private InstConfCache instConfCache;
    @Autowired
    private InterfaceConfCache interfaceConfCache;
    @Autowired
    private HandlerChainFactory handlerChainFactory;
    @Autowired
    private FunctionCache functionCache;

    @Override
    public void run(EngineContext context) {
        initContext(context);
        List<BaseHandler> handlers = handlerChainFactory.getHandlerChain(context.getHandlerChainType());
        assertTrue(!CollectionUtils.isEmpty(handlers), "can not find handler chain, handlerChainType:  "
                + context.getHandlerChainType());

        handlers.forEach(handler -> handler.run(context));
    }

    private void initContext(EngineContext context) {

        context.setUseMock(RuntimeHelper.isUseMock());

        context.setInterfaceConf(interfaceConfCache.getInterfaceConfByName(context.getInterfaceName()));
        assertNotNull(context.getInterfaceConf(), "can not find interface: " + context.getInterfaceName());

        context.setHandlerChainType(context.getInterfaceConf().getHandlerChainType());
        assertNotNull(context.getHandlerChainType(), "handlerChainType can not be null!");

        context.setFunctionData(functionCache.getAllFunctions());
        context.setInstConfData(instConfCache.getInstConfByGroupName(context.getInterfaceConf().getInstConfGroupName()).getValues());

        context.initRuntimeContext();
    }
}
