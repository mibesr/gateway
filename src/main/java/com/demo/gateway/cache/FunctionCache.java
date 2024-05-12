package com.demo.gateway.cache;

import com.demo.gateway.engine.function.BaseFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class FunctionCache {
    @Autowired
    private List<BaseFunction> functionList;

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
