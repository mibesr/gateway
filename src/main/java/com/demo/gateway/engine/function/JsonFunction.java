package com.demo.gateway.engine.function;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Component;

/**
 * JSON函数
 *
 * @author 隐墨星辰
 */
@Component
public class JsonFunction extends BaseFunction {

    public String toJSONString(Object object) {
        return JSON.toJSONString(object);
    }
}
