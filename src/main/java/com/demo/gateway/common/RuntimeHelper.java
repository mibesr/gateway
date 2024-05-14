package com.demo.gateway.common;

/**
 * 运行时帮助类
 *
 * @author 隐墨星辰
 */
public class RuntimeHelper {
    private static final ThreadLocal<RuntimeHelper> RUNTIME_HELPER = new ThreadLocal<>();

    /**
     * 是否使用mock，前期调试使用
     */
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

    public static void clear() {
        RUNTIME_HELPER.remove();
    }
}
