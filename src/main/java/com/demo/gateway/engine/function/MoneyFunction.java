package com.demo.gateway.engine.function;

import com.demo.gateway.common.Money;
import org.springframework.stereotype.Component;

/**
 * Money处理函数
 *
 * @author 隐墨星辰
 */
@Component
public class MoneyFunction extends BaseFunction {

    /**
     * 创建Money对象
     *
     * @param minorUnits
     * @param currency
     * @return
     */
    public static Money of(long minorUnits, String currency) {
        return Money.of(minorUnits, currency);
    }

}
