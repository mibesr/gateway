package com.demo.gateway.engine.impl;

import com.demo.gateway.cache.FunctionCache;
import com.demo.gateway.cache.InstConfCache;
import com.demo.gateway.cache.InterfaceConfCache;
import com.demo.gateway.common.RuntimeHelper;
import com.demo.gateway.engine.HandlerEngineService;
import com.demo.gateway.engine.context.HandlerEngineContext;
import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.chain.HandlerChainFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;

/**
 * 处理器链引擎服务实现
 *
 * @author 隐墨星辰
 */
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
    public void run(HandlerEngineContext context) {
        initContext(context);

        // 找到处理器链
        List<BaseHandler> handlers = handlerChainFactory.getHandlerChain(context.getHandlerChainType());
        assertTrue(!CollectionUtils.isEmpty(handlers), "can not find handler chain, handlerChainType:  "
                + context.getHandlerChainType());

        // 依次执行
        handlers.forEach(handler -> handler.run(context));
    }

    private void initContext(HandlerEngineContext context) {

        // TODO 生产上，需要根据环境或条件来判断是否走mock
        context.setUseMock(RuntimeHelper.isUseMock());

        // 设置接口配置
        context.setInterfaceConf(interfaceConfCache.getInterfaceConfByName(context.getInterfaceName()));
        assertNotNull(context.getInterfaceConf(), "can not find interface: " + context.getInterfaceName());

        // 设置机构配置
        context.setInstConfData(instConfCache.getInstConfByGroupName(context.getInterfaceConf().getInstConfGroupName()).getValues());
        assertNotNull(context.getInstConfData(), "can not find instConfData: " + context.getInterfaceName());

        // 设置处理器类类型
        context.setHandlerChainType(context.getInterfaceConf().getHandlerChainType());
        assertNotNull(context.getHandlerChainType(), "handlerChainType can not be null!");

        // 设置内联函数
        context.setFunctionData(functionCache.getAllFunctions());

        context.initRuntimeContext();
    }
}
