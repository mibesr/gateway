package com.demo.gateway.engine.context;

import com.demo.gateway.engine.function.BaseFunction;
import com.demo.gateway.engine.handler.chain.HandlerChainType;
import com.demo.gateway.model.InstConf;
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
    private Map<String, Object> originalRequestData;
    private Map<String, Object> signData;
    private Map<String, Object> verifyData;
    private Map<String, Object> originalResponseData;
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

    public void initRuntimeContext() {
        originalRequestData = new HashMap<>();
        signData = new HashMap<>();
        verifyData = new HashMap<>();
        originalResponseData = new HashMap<>();

        runtimeContext = new HashMap<>();
        runtimeContext.put("_O_REQ", originalRequestData);
        runtimeContext.put("_O_RSP", originalResponseData);
        runtimeContext.put("_F", functionData);
        runtimeContext.put("_C", instConfData);
        runtimeContext.put("_S", signData);
        runtimeContext.put("_V", verifyData);
    }
}
