package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class SendConf {
    private String host;
    private String url;
    private HeadConf headConf;
}