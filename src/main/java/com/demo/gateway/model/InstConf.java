package com.demo.gateway.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;


/**
 * 机构配置
 *
 * @author 隐墨星辰（公众号同名）
 */
@Data
@Builder
@ToString
@AllArgsConstructor
public class InstConf {
    /**
     * 分组名，比如icbu_channel，就是工行配置
     */
    private String groupName;

    private Map<String, String> values;
}
