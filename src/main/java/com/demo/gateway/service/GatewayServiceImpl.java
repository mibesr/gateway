package com.demo.gateway.service;

import com.demo.gateway.api.GatewayService;
import com.demo.gateway.api.request.GatewayRequest;
import com.demo.gateway.api.response.GatewayResponse;
import com.demo.gateway.engine.HandlerEngineService;
import com.demo.gateway.engine.context.HandlerEngineContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class GatewayServiceImpl implements GatewayService {

    @Autowired
    private HandlerEngineService engineService;

    @Override
    public GatewayResponse process(GatewayRequest request) {

        try {
            HandlerEngineContext context = buildContext(request);
            engineService.run(context);

            // TODO LOG
            System.out.println("requestContext: " + context.getRequestContext());
            System.out.println("requestMessageTemplate: " + context.getInterfaceConf().getMessageConf().getRequestMessageTemplate());
            System.out.println("assembledRequestMessage: " + context.getAssembledRequestMessage());
            System.out.println("originalResponseMessage: " + context.getOriginalResponseMessage());
            System.out.println("responseMessageTemplate: " + context.getInterfaceConf().getMessageConf().getResponseMessageTemplate());
            System.out.println("assembledResponseMessage: " + context.getAssembledResponseMessage());

            return buildResponse(context.getAssembledResponseMessage());
        } catch (Exception e) {
            //TODO LOG
            e.printStackTrace();

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
