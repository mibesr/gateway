package com.demo.gateway.cache;

import com.demo.gateway.engine.function.BaseFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 内联函数缓存
 *
 * @author 隐墨星辰
 */
@Component
public class FunctionCache {

    /**
     * 内联函数列表，系统自动初始化并赋值
     */
    @Autowired
    private List<BaseFunction> functionList;

    /**
     * 转成map，方便groovy模板使用
     */
    private Map<String, BaseFunction> functionMap;

    @PostConstruct
    private void init() {
        functionMap = functionList.stream().collect(
                Collectors.toMap(BaseFunction::getName, function -> function));
    }

    public Map<String, BaseFunction> getAllFunctions() {
        return functionMap;
    }
}
