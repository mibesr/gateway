package com.demo.gateway.model;

import com.demo.gateway.engine.handler.chain.HandlerChainType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class InterfaceConf {
    private String name;
    private HandlerChainType handlerChainType;
    private String instConfGroupName;
    private SignConf signConf;
    private VerifyConf verifyConf;
    private SendConf sendConf;
    private MessageType messageType;
    private MessageConf messageConf;
    private MockConf mockConf;
}
