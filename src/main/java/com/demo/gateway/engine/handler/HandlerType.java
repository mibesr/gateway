package com.demo.gateway.engine.handler;

public enum HandlerType {
    CONVERT_ORIGINAL_REQUEST_MESSAGE,
    SIGN,
    ASSEMBLE_REQUEST_MESSAGE,
    ASSEMBLE_REQUEST_HEAD,
    SEND,
    PARSE_ORIGINAL_RESPONSE_MESSAGE,
    PARSE_HEAD_MESSAGE,
    VERIFY,
    ASSEMBLE_RESPONSE_MESSAGE
}