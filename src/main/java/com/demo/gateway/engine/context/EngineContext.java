package com.demo.gateway.engine.context;

import com.demo.gateway.engine.function.BaseFunction;
import com.demo.gateway.engine.handler.chain.HandlerChainType;
import com.demo.gateway.model.InterfaceConf;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@ToString
public class EngineContext {
    private Map<String, Object> requestData;
    private Map<String, Object> responseData;
    private Map<String, BaseFunction> functionData;
    private Map<String, String> instConfData;
    private String assembledRequestMessage;
    private String assembledResponseMessage;
    private String originalResponseMessage;
    private String requestContext;
    private String interfaceName;
    private HandlerChainType handlerChainType;
    private InterfaceConf interfaceConf;
    private Map<String, Object> runtimeContext;
    private boolean useMock;

    public void initRuntimeContext() {
        requestData = new HashMap<>();
        responseData = new HashMap<>();

        runtimeContext = new HashMap<>();
        runtimeContext.put("requestData", requestData);
        runtimeContext.put("responseData", responseData);
        runtimeContext.put("instConfData", instConfData);
        runtimeContext.put("function", functionData);
    }
}
