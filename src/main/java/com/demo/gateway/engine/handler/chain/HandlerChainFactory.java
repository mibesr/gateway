package com.demo.gateway.engine.handler.chain;

import com.demo.gateway.engine.handler.BaseHandler;
import com.demo.gateway.engine.handler.HandlerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HandlerChainFactory {
    private static final List<HandlerType> INNER_TO_OUTER_HANDLER_TYPE_CHAIN = Arrays.asList(
            HandlerType.CONVERT_ORIGINAL_REQUEST_MESSAGE,
            HandlerType.SIGN,
            HandlerType.ASSEMBLE_REQUEST_MESSAGE,
            HandlerType.ASSEMBLE_REQUEST_HEAD,
            HandlerType.SEND,
            HandlerType.PARSE_ORIGINAL_RESPONSE_MESSAGE,
            HandlerType.PARSE_HEAD_MESSAGE,
            HandlerType.CONVERT_ORIGINAL_REQUEST_MESSAGE,
            HandlerType.ASSEMBLE_RESPONSE_MESSAGE
    );

    private static final List<HandlerType> OUTER_TO_INNER_HANDLER_TYPE_CHAIN = Collections.emptyList();

    private final Map<HandlerChainType, List<BaseHandler>> handlerChains = new HashMap<>();

    @Autowired
    private List<BaseHandler> handlerList;

    @PostConstruct
    public void init() {
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
