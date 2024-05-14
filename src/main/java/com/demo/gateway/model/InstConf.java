package com.demo.gateway.model;

import lombok.*;

import java.util.Map;


/**
 * 机构配置
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InstConf {
    /**
     * 分组名，比如icbu_channel，就是工行配置
     */
    private String groupName;

    private Map<String, String> values;
}
