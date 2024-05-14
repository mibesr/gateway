package com.demo.gateway.service;

import com.demo.gateway.api.GatewayService;
import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;
import com.demo.gateway.engine.HandlerEngineService;
import com.demo.gateway.engine.context.HandlerEngineContext;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 隐墨星辰
 */
@Component
@Log4j2
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private HandlerEngineService engineService;

    @Override
    public GatewayResponse process(GatewayRequest request) {

        try {
            HandlerEngineContext context = buildContext(request);
            engineService.run(context);

            log.info("requestContext: {}", context.getRequestContext());
            log.info("requestMessageTemplate: {}", context.getInterfaceConf().getMessageConf().getRequestMessageTemplate());
            log.info("assembledRequestMessage: {}", context.getAssembledRequestMessage());
            log.info("originalResponseMessage: {}", context.getOriginalResponseMessage());
            log.info("responseMessageTemplate: {}", context.getInterfaceConf().getMessageConf().getResponseMessageTemplate());
            log.info("assembledResponseMessage: {}", context.getAssembledResponseMessage());

            return buildResponse(context.getAssembledResponseMessage());
        } catch (Exception e) {
            log.error("An error occurred, request: {}", request, e);

            return GatewayResponse.builder()
                    .success(false)
                    .errorMessage(e.getMessage())
                    .build();
        }
    }

    private GatewayResponse buildResponse(String assembledResponseMessage) {
        return GatewayResponse.builder()
                .success(true)
                .responseContext(assembledResponseMessage)
                .build();
    }

    private HandlerEngineContext buildContext(GatewayRequest request) {
        return HandlerEngineContext.builder()
                .requestContext(request.getRequestContext())
                .interfaceName(request.getInterfaceName())
                .build();
    }
}
