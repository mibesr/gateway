package com.demo.gateway.engine.context;

import com.demo.gateway.engine.function.BaseFunction;
import com.demo.gateway.engine.handler.chain.HandlerChainType;
import com.demo.gateway.model.InterfaceConf;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理器引擎上下文
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
public class HandlerEngineContext {
    /**
     * 请求参数
     * 包括原始请求参数，签名参数等
     */
    private Map<String, Object> requestData;

    /**
     * 返回参数
     * 包括原始返回参数，验签参数等
     */
    private Map<String, Object> responseData;

    /**
     * 内联函数
     */
    private Map<String, BaseFunction> functionData;

    /**
     * 机构配置
     */
    private Map<String, String> instConfData;

    /**
     * 组装后的报文，用于发送给出去
     */
    private String assembledRequestMessage;

    /**
     * 组装后的报文，用于返回给请求来源
     */
    private String assembledResponseMessage;

    /**
     * 请求外部后，返回的原始报文
     * 比如发给外部渠道，返回的原始报文
     */
    private String originalResponseMessage;

    /**
     * 系统请求到网关的原始请求参数
     */
    private String requestContext;

    /**
     * 接口名称
     * 比如：icbu.pay，就是工行的支付接口
     */
    private String interfaceName;

    /**
     * 处理器责任链类型
     * 内部发外部，或外部发内部
     */
    private HandlerChainType handlerChainType;

    /**
     * 接口配置
     */
    private InterfaceConf interfaceConf;

    /**
     * 运行时上下文，用于给groovy脚本引擎处理变量替换
     */
    private Map<String, Object> runtimeContext;

    /**
     * 是否使用mock
     */
    private boolean useMock;

    public void initRuntimeContext() {
        requestData = new HashMap<>();
        responseData = new HashMap<>();

        runtimeContext = new HashMap<>();

        /**
         * requestData: 请求参数
         * responseData：返回参数
         * instConfData：机构配置参数
         * function：内联函数
         *
         * 在请求报文模板中，需要这样使用:
         * "order_id": "${requestData.orderNo}"
         * "merchant_id": "${instConfData.merchantId}"
         * "date": "${function.Datetime.formatDateString(requestData.requestTime, 'yyyy-MM-dd HH:mm:ss')}"
         */
        runtimeContext.put("requestData", requestData);
        runtimeContext.put("responseData", responseData);
        runtimeContext.put("instConfData", instConfData);
        runtimeContext.put("function", functionData);
    }
}
