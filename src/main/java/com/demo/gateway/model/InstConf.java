package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@ToString
@AllArgsConstructor
public class InstConf {
    private String groupName;
    private Map<String, String> values;
}
