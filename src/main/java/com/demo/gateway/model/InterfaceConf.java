package com.demo.gateway.model;

import com.demo.gateway.common.MessageType;
import com.demo.gateway.engine.chain.HandlerChainType;
import lombok.*;


/**
 * 渠道接口配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InterfaceConf {
    /**
     * 名称
     */
    private String name;

    /**
     * 处理链类型
     */
    private HandlerChainType handlerChainType;

    /**
     * 机构配置分组名
     */
    private String instConfGroupName;

    /**
     * 签名配置
     */
    private SignConf signConf;

    /**
     * 验签配置
     */
    private VerifyConf verifyConf;

    /**
     * 发送配置
     */
    private HttpSendConf httpSendConf;

    /**
     * 报文类型
     */
    private MessageType messageType;

    /**
     * 报文配置
     */
    private MessageConf messageConf;

    /**
     * mock配置
     */
    private MockConf mockConf;
}
