package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class MessageConf {
    private String requestMessageTemplate;

    private String responseMessageTemplate;
}
