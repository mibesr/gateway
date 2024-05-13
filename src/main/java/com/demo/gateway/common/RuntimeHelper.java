package com.demo.gateway.common;

public class RuntimeHelper {
    private static ThreadLocal<RuntimeHelper> RUNTIME_HELPER = new ThreadLocal<>();

    private boolean useMock;

    public static void setUseMock(boolean useMock) {
        RUNTIME_HELPER.get().useMock = useMock;
    }

    public static boolean isUseMock() {
        return RUNTIME_HELPER.get().useMock;
    }

    public static void init() {
        RUNTIME_HELPER.set(new RuntimeHelper());
    }
}
