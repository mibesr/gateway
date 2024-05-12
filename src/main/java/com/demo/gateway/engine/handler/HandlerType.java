package com.demo.gateway.engine.handler;

/**
 * 处理器类型
 *
 * @author 隐墨星辰（公众号同名）
 */
public enum HandlerType {
    /**
     * 原始请求转换
     */
    CONVERT_ORIGINAL_REQUEST_MESSAGE,

    /**
     * 签名
     */
    SIGN,

    /**
     * 组装请求报文体
     */
    ASSEMBLE_REQUEST_MESSAGE,

    /**
     * 组装报文头
     */
    ASSEMBLE_REQUEST_HEAD,

    /**
     * 发送
     */
    SEND,

    /**
     * 解析原始报文体
     */
    PARSE_ORIGINAL_RESPONSE_MESSAGE,

    /**
     * 解析原始报文头
     */
    PARSE_HEAD_MESSAGE,

    /**
     * 验签
     */
    VERIFY,

    /**
     * 组装返回报文
     */
    ASSEMBLE_RESPONSE_MESSAGE
}
