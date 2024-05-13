package com.demo.gateway.engine.function;

import com.demo.gateway.common.Money;
import org.springframework.stereotype.Component;

/**
 * Money处理函数
 *
 * @author 隐墨星辰（公众号同名）
 */
@Component
public class MoneyFunction extends BaseFunction {
    public static Money of(long amount, String currency) {
        return new Money(amount, currency);
    }

    @Override
    public String getName() {
        return "Money";
    }
}
