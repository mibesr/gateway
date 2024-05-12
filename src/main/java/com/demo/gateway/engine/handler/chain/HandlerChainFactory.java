package com.demo.gateway.engine.handler.chain;

import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 处理器链工厂
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class HandlerChainFactory {

    /**
     * 内发外处理器链
     */
    private static final List<HandlerType> INNER_TO_OUTER_HANDLER_TYPE_CHAIN = Arrays.asList(
            HandlerType.CONVERT_ORIGINAL_REQUEST_MESSAGE,
            HandlerType.SIGN,
            HandlerType.ASSEMBLE_REQUEST_MESSAGE,
            HandlerType.ASSEMBLE_REQUEST_HEAD,
            HandlerType.SEND,
            HandlerType.PARSE_ORIGINAL_RESPONSE_MESSAGE,
            HandlerType.PARSE_HEAD_MESSAGE,
            HandlerType.VERIFY,
            HandlerType.CONVERT_ORIGINAL_REQUEST_MESSAGE,
            HandlerType.ASSEMBLE_RESPONSE_MESSAGE
    );

    /**
     * 外发内处理器链
     */
    private static final List<HandlerType> OUTER_TO_INNER_HANDLER_TYPE_CHAIN = Collections.emptyList();

    /**
     * 根据类型分类的处理器链
     */
    private final Map<HandlerChainType, List<BaseHandler>> handlerChains = new HashMap<>();

    /**
     * spring自动把所有处理器加载到这个变量里
     */
    @Autowired
    private List<BaseHandler> handlerList;

    @PostConstruct
    public void init() {
        // 转成map
        Map<HandlerType, BaseHandler> allHandlers = handlerList.stream().collect(
                Collectors.toMap(BaseHandler::getHandlerType, handler -> handler)
        );

        initInnerToOuterHandlerChain(allHandlers);
        initOuterToInnerHandlerChain(allHandlers);
    }

    private void initInnerToOuterHandlerChain(Map<HandlerType, BaseHandler> allHandlers) {
        List<BaseHandler> chain = INNER_TO_OUTER_HANDLER_TYPE_CHAIN.stream().filter(allHandlers::containsKey)
                .map(allHandlers::get).collect(Collectors.toList());
        handlerChains.put(HandlerChainType.INNER_TO_OUTER, chain);
    }

    private void initOuterToInnerHandlerChain(Map<HandlerType, BaseHandler> allHandlers) {
        List<BaseHandler> chain = OUTER_TO_INNER_HANDLER_TYPE_CHAIN.stream().filter(allHandlers::containsKey)
                .map(allHandlers::get).collect(Collectors.toList());
        handlerChains.put(HandlerChainType.OUTER_TO_INNER, chain);
    }

    public List<BaseHandler> getHandlerChain(HandlerChainType chainType) {
        return handlerChains.get(chainType);
    }

}
