package com.demo.gateway.model;

import lombok.*;

/**
 * 密钥
 *
 * @author 隐墨星辰
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class KeyConf {

    private String indexName;

    private String type;

    private String value;
}
